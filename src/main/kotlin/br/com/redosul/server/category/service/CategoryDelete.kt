package br.com.redosul.server.category.service

import br.com.redosul.server.category.repository.CategoryJpaRepository
import br.com.redosul.server.category.repository.CategoryRepository
import br.com.redosul.server.category.type.CategoryId
import org.springframework.stereotype.Service

@Service
class CategoryDelete(
    private val repository: CategoryRepository
) {
    operator fun invoke(
        id: CategoryId
    ) = repository.deleteById(id)
}