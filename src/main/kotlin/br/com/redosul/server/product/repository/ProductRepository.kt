package br.com.redosul.server.product.repository

import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import br.com.redosul.server.product.type.ProductId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Repository
class ProductRepository(
    private val jpa: ProductJpaRepository
) {
    fun save(product: Product) = jpa.save(product)

    @OptIn(ExperimentalStdlibApi::class)
    fun findById(id: ProductId) = jpa.findById(id.value).getOrNull()

    fun findAll(categoryId: CategoryId?) = categoryId?.let {
        jpa.findAllByCategoryId(it.value)
    } ?: jpa.findAll()
}