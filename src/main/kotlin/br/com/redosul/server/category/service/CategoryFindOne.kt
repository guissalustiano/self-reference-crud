package br.com.redosul.server.category.service

import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryFindOne(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        categoryId: Long
    ) = kotlin.runCatching {
        repository.findByIdOrNull(categoryId) ?: throw CategoryException.NotFound(categoryId)
    }
}