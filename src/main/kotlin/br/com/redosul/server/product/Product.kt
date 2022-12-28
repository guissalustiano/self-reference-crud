package br.com.redosul.server.product

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.Category
import br.com.redosul.server.product.type.ProductId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.NaturalId

// TODO: separate in base product, product variant and provider
// think about inventory, onder and supplier

@Entity
class Product(
    @NaturalId var code: Code,
    @Column var name: Name,
    @Column var description: Description?,
    @ManyToOne(fetch = FetchType.LAZY) var category: Category,
    @NaturalId var slug: Slug = name.toSlug(),
    id: ProductId = ProductId.ZERO,
): LongIdEntity(id.value) {
    init {
        category.addProduct(this)
    }

    fun getId() = ProductId(id)

    companion object {}
}