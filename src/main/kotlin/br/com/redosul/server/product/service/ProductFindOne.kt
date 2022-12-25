package br.com.redosul.server.product.service

import br.com.redosul.server.product.exception.ProductException
import br.com.redosul.server.product.repository.ProductRepository
import br.com.redosul.server.product.type.ProductId
import org.springframework.stereotype.Service

@Service
class ProductFindOne(
    private val repository: ProductRepository
) {
    operator fun invoke(id: ProductId) = repository.findById(id) ?: throw ProductException.NotFound(id)
}