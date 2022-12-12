package br.com.redosul.server.product.repository

import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
class ProductRepository(
    private val jpa: ProductJpaRepository
) {
    fun save(product: Product) = jpa.save(product)
    fun findAll(categoryId: CategoryId?) = categoryId?.let {
        jpa.findAllByCategoryId(it.value)
    } ?: jpa.findAll()
}