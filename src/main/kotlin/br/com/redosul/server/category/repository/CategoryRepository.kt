package br.com.redosul.server.category.repository;

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.base.type.Slug
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.type.CategoryId
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryRepository(
    private val jpa: CategoryJpaRepository
) {
    fun findByCodeOrNameOrSlugAllIgnoreCase(code: Code, name: Name, slug: Slug) = jpa.findByCodeOrNameOrSlugAllIgnoreCase(code.value, name.value, slug.value)
    fun deleteById(id: CategoryId) = jpa.deleteById(id.value)
    @OptIn(ExperimentalStdlibApi::class)
    fun findById(id: CategoryId) = jpa.findById(id.value).getOrNull()

    fun search(name: String?) = name?.let { jpa.findByNameContainsIgnoreCase(it) } ?: jpa.findAll()
    fun save(category: Category) = jpa.save(category)
}