package br.com.redosul.server.category

import br.com.redosul.server.base.EmbbededIdEntity
import jakarta.persistence.*


@Entity
class CategoryClousure(
    parentId: Long = 0,
    childId: Long = 0,
): EmbbededIdEntity<CategoryClousureId>(CategoryClousureId(parentId, childId)) {
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("parentId")
    lateinit var parent: Category

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("childId")
    lateinit var child: Category

    constructor(parent: Category, child: Category): this(parent.id, child.id) {
        this.parent = parent
        this.child = child
    }

    override fun isPersisted() = this.id != CategoryClousureId.ZERO
}