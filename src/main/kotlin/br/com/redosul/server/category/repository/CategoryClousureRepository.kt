package br.com.redosul.server.category.repository;

import br.com.redosul.server.category.CategoryClousure
import br.com.redosul.server.category.type.CategoryClousureId
import org.springframework.data.repository.CrudRepository

interface CategoryClousureRepository : CrudRepository<CategoryClousure, CategoryClousureId>