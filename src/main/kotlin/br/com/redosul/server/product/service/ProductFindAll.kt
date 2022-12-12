package br.com.redosul.server.product.service

import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductFindAll(
    private val repository: ProductRepository
) {
    operator fun invoke(categoryId: CategoryId?) = repository.findAll(categoryId)
}