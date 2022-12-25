package br.com.redosul.server.category

import br.com.redosul.server.base.entity.EmbbededIdEntity
import br.com.redosul.server.category.type.CategoryClousureId
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.*

@Entity
// Teoric is a nested private class from category, and cannot be used directly
// but because of JPA limitantions, we need to use this class as a separated entity
class CategoryClousure(
    id: CategoryClousureId = CategoryClousureId.ZERO,
    @Column var depth: UInt
) : EmbbededIdEntity<CategoryClousureId>(id) {
    val parentId: CategoryId
        get() = CategoryId(id.parentId)

    val childId: CategoryId
        get() = CategoryId(id.parentId)


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("parentId")
    lateinit var parent: Category

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("childId")
    lateinit var child: Category

    constructor(parent: Category, child: Category, depth: UInt) : this(
        CategoryClousureId(
            parent.getId().value,
            child.getId().value
        ),
        depth
    ) {
        this.parent = parent
        this.child = child
    }

    override fun isPersisted() = this.id != CategoryClousureId.ZERO
}