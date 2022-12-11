package br.com.redosul.server.product

import br.com.redosul.server.base.LongIdEntity
import br.com.redosul.server.base.extension.toSlug
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.NaturalId

@Entity
class Product(
    @NaturalId var code: String,
    @Column var name: String,
    @Column var description: String?,
    @NaturalId var slug: String = name.toSlug(),
    id: Long = 0,
): LongIdEntity(id)