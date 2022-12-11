package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryException
import br.com.redosul.server.category.CategoryRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.stereotype.Service

@Service
class CategoryCreate(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        category: Category
    ) = kotlin.runCatching {
        guard(category)
        repository.save(category)
    }.recoverCatching { e ->
        // rare cases of race condition
        if (e is ConstraintViolationException) {
            handleConstraint(e, category)
        }
        throw e
    }

    private fun guard(category: Category) {
        repository.findByCodeOrNameOrSlugAllIgnoreCase(category.code, category.name, category.slug)?.let {
            if (it.id != category.id) {
                if (it.code == category.code) {
                    throw CategoryException.AlreadyExistCode(category.code)
                }
                if (it.name == category.name) {
                    throw CategoryException.AlreadyExistsName(category.name)
                }
                if (it.slug == category.slug) {
                    throw CategoryException.AlreadyExistsSlug(category.slug)
                }
            }
        }
    }

    private fun handleConstraint(e: ConstraintViolationException, category: Category): Nothing {
         e.constraintName.let {
            when {
                it.endsWith("code") -> throw CategoryException.AlreadyExistCode(category.code)
                it.endsWith("name") -> throw CategoryException.AlreadyExistsName(category.name)
                it.endsWith("slug") -> throw CategoryException.AlreadyExistsSlug(category.slug)
                else -> throw e
            }
        }
    }
}