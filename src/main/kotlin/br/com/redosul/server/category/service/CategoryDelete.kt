package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryException
import br.com.redosul.server.category.CategoryRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.stereotype.Service

@Service
class CategoryDelete(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        categoryId: Long
    ) = repository.deleteById(categoryId)
}