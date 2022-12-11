package br.com.redosul.server.category;

import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByNameContainsIgnoreCase(name: String): List<Category>
    fun findByCodeOrNameOrSlugAllIgnoreCase(code: String, name: String, description: String): Category?
}