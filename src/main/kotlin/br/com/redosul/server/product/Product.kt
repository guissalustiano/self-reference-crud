package br.com.redosul.server.product

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.type.*
import br.com.redosul.server.product.type.ProductId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.NaturalId

@Entity
class Product(
    @NaturalId var code: Code,
    @Column var name: Name,
    @Column var description: Description?,
    @NaturalId var slug: Slug = name.toSlug(),
    id: ProductId = ProductId.ZERO,
): LongIdEntity(id.value)