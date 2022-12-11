package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.repository.CategoryJpaRepository
import br.com.redosul.server.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategorySearchAll(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        nameQuery: String? = null
    ): Iterable<Category> = repository.search(nameQuery)
}