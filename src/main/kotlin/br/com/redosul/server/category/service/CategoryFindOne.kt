package br.com.redosul.server.category.service

import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.repository.CategoryRepository
import br.com.redosul.server.category.type.CategoryId
import org.springframework.stereotype.Service

@Service
class CategoryFindOne(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        categoryId: CategoryId
    ) = repository.findById(categoryId)
}