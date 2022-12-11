package br.com.redosul.server.category.service

import br.com.redosul.server.category.CategoryException
import br.com.redosul.server.category.CategoryRepository
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