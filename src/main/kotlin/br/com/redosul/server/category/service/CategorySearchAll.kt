package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategorySearchAll(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        nameQuery: String? = null
    ): Iterable<Category> = if (nameQuery.isNullOrBlank())
        repository.findAll()
    else
        repository.findByNameContainsIgnoreCase(nameQuery)
}