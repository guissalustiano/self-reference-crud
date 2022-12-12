package br.com.redosul.server.category

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany


@Entity
class Category(
    @Column var code: Code,
    @Column var name: Name,
    @Column var description: Description?,
    @Column var slug: Slug = name.toSlug(),
    id: CategoryId = CategoryId.ZERO,
): LongIdEntity(id.value) {
    fun getId() = CategoryId(id)
    fun addChildConnection(connection: CategoryClousure) {
        _childrenConnection.add(connection)
    }

    fun addParentConnection(connection: CategoryClousure) {
        _parentConnections.add(connection)
    }

    @OneToMany(mappedBy = "child")
    private var _parentConnections: MutableList<CategoryClousure> = mutableListOf()

    @OneToMany(mappedBy = "parent")
    private var _childrenConnection: MutableList<CategoryClousure> = mutableListOf()

    val parentConnections: List<CategoryClousure>
        get() = _parentConnections.toList()

    val childrenConnection: List<CategoryClousure>
        get() = _childrenConnection.toList()

    private val selfConnection: CategoryClousure
        get() = parentConnections.first { it.parentId == getId() && it.childId == getId() }


    val depth: UInt
        get() = selfConnection.depth

    val directParent: Category?
        get() = parentConnections
            .filterNot { it == selfConnection }
            .map { it.parent }
            .firstOrNull { it.depth == depth - 1u }

    val directChildren: List<Category>
        get() = childrenConnection.
        filterNot { it == selfConnection }
            .filter { it.depth == depth + 1u }
            .map { it.child }
}
