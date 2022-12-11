package br.com.redosul.server.category.service

import br.com.redosul.server.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryDelete(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        categoryId: Long
    ) = repository.deleteById(categoryId)
}