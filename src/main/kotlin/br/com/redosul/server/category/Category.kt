package br.com.redosul.server.category

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.extension.toSlug
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import org.hibernate.annotations.NaturalId


@Entity
class Category(
    @NaturalId var code: String,
    @Column var name: String,
    @Column var description: String?,
    @NaturalId var slug: String = name.toSlug(),
    id: Long = 0,
): LongIdEntity(id) {

    @OneToMany(mappedBy = "parent")
    private lateinit var _parents: List<CategoryClousure>

    @OneToMany(mappedBy = "child")
    private lateinit var _children: List<CategoryClousure>

    val parents: List<Category>
        get() = _parents.map { it.parent }

    val children: List<Category>
        get() = _children.map { it.child }
}