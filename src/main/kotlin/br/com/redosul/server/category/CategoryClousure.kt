package br.com.redosul.server.category

import br.com.redosul.server.base.entity.EmbbededIdEntity
import br.com.redosul.server.category.type.CategoryClousureId
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.*

@Entity
class CategoryClousure(
    parentId: CategoryId = CategoryId.ZERO,
    childId: CategoryId = CategoryId.ZERO,
): EmbbededIdEntity<CategoryClousureId>(CategoryClousureId(parentId.value, childId.value)) {
    val parentId: CategoryId
        get() = CategoryId(id.parentId)



    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("parentId")
    lateinit var parent: Category

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("childId")
    lateinit var child: Category

    constructor(parent: Category, child: Category): this(parent.getId(), child.getId()) {
        this.parent = parent
        this.child = child
    }

    override fun isPersisted() = this.id != CategoryClousureId.ZERO
}