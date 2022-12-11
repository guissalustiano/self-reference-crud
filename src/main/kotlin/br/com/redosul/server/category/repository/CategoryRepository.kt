package br.com.redosul.server.category.repository;

import br.com.redosul.server.category.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByNameContainsIgnoreCase(name: String): List<Category>
    fun findByCodeOrNameOrSlugAllIgnoreCase(code: String, name: String, description: String): Category?
}