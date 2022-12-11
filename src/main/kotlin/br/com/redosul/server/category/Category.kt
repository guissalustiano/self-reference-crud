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

    @OneToMany(mappedBy = "parent")
    private lateinit var _parents: List<CategoryClousure>

    @OneToMany(mappedBy = "child")
    private lateinit var _children: List<CategoryClousure>

    val parents: List<Category>
        get() = _parents.map { it.parent }

    val children: List<Category>
        get() = _children.map { it.child }
}
