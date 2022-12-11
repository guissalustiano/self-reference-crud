package br.com.redosul.server.category

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import org.hibernate.annotations.NaturalId


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
        _children.add(connection)
    }

    fun addParentConnection(connection: CategoryClousure) {
        _parents.add(connection)
    }

    val depth: UInt
        get() = _parents.first().depth

    @OneToMany(mappedBy = "child")
    private var _parents: MutableList<CategoryClousure> = mutableListOf()

    @OneToMany(mappedBy = "parent")
    private var _children: MutableList<CategoryClousure> = mutableListOf()

    private val parents: List<Category>
        get() = _parents.map { it.parent }.filterNot { it == this }

    val parent: Category?
        get() = parents.firstOrNull { it.depth > 0u && it.depth == depth - 1u }

    val children: List<Category>
        get() = _children.map { it.child }.filterNot { it == this }
}
