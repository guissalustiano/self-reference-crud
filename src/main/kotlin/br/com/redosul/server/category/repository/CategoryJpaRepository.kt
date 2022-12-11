package br.com.redosul.server.category.repository;

import br.com.redosul.server.category.Category
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CategoryJpaRepository : CrudRepository<Category, Long> {

    @EntityGraph(attributePaths = ["_parents"])
    override fun findById(id: Long): Optional<Category>

    @EntityGraph(attributePaths = ["_parents"])
    override fun findAll(): List<Category>

    fun findByNameContainsIgnoreCase(name: String): List<Category>

    fun findByCodeOrNameOrSlugAllIgnoreCase(code: String, name: String, slug: String): Category?
}