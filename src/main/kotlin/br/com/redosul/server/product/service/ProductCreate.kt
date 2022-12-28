package br.com.redosul.server.product.service

import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.service.CategoryFindOne
import br.com.redosul.server.product.Product
import br.com.redosul.server.product.message.ProductCreatePayload
import br.com.redosul.server.product.message.toProduct
import br.com.redosul.server.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductCreate(
    private val repository: ProductRepository,
    private val categoryFindOne: CategoryFindOne,
) {
    operator fun invoke(payload: ProductCreatePayload): Product {
        val product = payload.toProduct{
                categoryId -> categoryFindOne(categoryId) ?: throw CategoryException.NotFound(categoryId)
        }
        return repository.save(product)
    }
}