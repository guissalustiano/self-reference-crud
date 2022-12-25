package br.com.redosul.server.category

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import jakarta.persistence.CascadeType
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

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "child"
    )
    private var _parentConnections: MutableList<CategoryClousure> = mutableListOf()

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "parent"
    )
    private var _childrenConnection: MutableList<CategoryClousure> = mutableListOf()

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "category"
    )
    private var _products: MutableList<Product> = mutableListOf()

    init {
        val selfReference = CategoryClousure(
            this,
            this,
            0u
        )
        this.addChildConnection(selfReference)
        this.addParentConnection(selfReference)
    }


    fun getId() = CategoryId(id)

    val products: Set<CategoryClousure>
        get() = _childrenConnection.toSet()

    private fun addChildConnection(connection: CategoryClousure) {
        _childrenConnection.add(connection)
    }

    private fun addParentConnection(connection: CategoryClousure) {
        _parentConnections.add(connection)
    }

    fun addProduct(product: Product) {
        _products.add(product)
    }

    private val parentConnections: Set<CategoryClousure>
        get() = _parentConnections.toSet()

    private val childrenConnection: Set<CategoryClousure>
        get() = _childrenConnection.toSet()

    private val selfConnection: CategoryClousure
        get() = parentConnections.first { it.parentId == getId() && it.childId == getId() }


    val depth: UInt
        get() = selfConnection.depth

    val allParents: Set<Category>
        get() = parentConnections.filterNot { it == selfConnection }.map { it.parent }.toSet()

    val allChildrens: Set<Category>
        get() = childrenConnection.filterNot { it == selfConnection }.map { it.child }.toSet()


    val parent: Category?
        get() = allParents
            .firstOrNull { it.depth == depth - 1u }

    val children: Set<Category>
        get() = allChildrens
            .filter { it.depth == depth + 1u }.toSet()

    fun addChildren(
        child: Category,
    ) {
        child.selfConnection.depth = depth + 1u

        parentConnections.map {
            CategoryClousure(
                it.parent,
                child,
                it.depth + 1u
            )
        }.onEach {
            child.addParentConnection(it)
            it.parent.addChildConnection(it)
        }
    }

    companion object {}
}
