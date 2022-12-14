package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryClousure
import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.repository.CategoryRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.stereotype.Service

@Service
class CategorySave(
    private val repository: CategoryRepository,
) {
    operator fun invoke(
        category: Category,
        clousures: List<CategoryClousure>
    ) = runCatching {
        guard(category)
        repository.save(category)
    }.recoverCatching { e ->
        // rare cases of race condition
        if (e is ConstraintViolationException) {
            handleConstraintException(e, category)
        }
        throw e
    }

    private fun guard(category: Category) {
        if (category.depth >= 3u) {
            throw CategoryException.TooDepth(category.depth)
        }

        repository.findByCodeOrNameOrSlugAllIgnoreCase(category.code, category.name, category.slug)?.let {
            if (it.getId() != category.getId()) {
                if (it.code == category.code) {
                    throw CategoryException.AlreadyExist.Code(category.code)
                }
                if (it.name == category.name) {
                    throw CategoryException.AlreadyExist.Name(category.name)
                }
                if (it.slug == category.slug) {
                    throw CategoryException.AlreadyExist.Slug(category.slug)
                }
            }
        }
    }

    private fun handleConstraintException(e: ConstraintViolationException, category: Category): Nothing {
         e.constraintName.let {
            when {
                it.endsWith("code") -> throw CategoryException.AlreadyExist.Code(category.code)
                it.endsWith("name") -> throw CategoryException.AlreadyExist.Name(category.name)
                it.endsWith("slug") -> throw CategoryException.AlreadyExist.Slug(category.slug)
                else -> throw e
            }
        }
    }
}