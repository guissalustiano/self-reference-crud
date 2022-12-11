package br.com.redosul.server.category.controller

import br.com.redosul.server.category.message.CategoryCreateRequest
import br.com.redosul.server.category.message.toEntity
import br.com.redosul.server.category.message.toResponse
import br.com.redosul.server.category.service.CategoryCreate
import br.com.redosul.server.category.service.CategoryDelete
import br.com.redosul.server.category.service.CategorySearchAll
import br.com.redosul.server.category.service.CategoryFindOne
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val findOne: CategoryFindOne,
    private val findAll: CategorySearchAll,
    private val create: CategoryCreate,
    private val delete: CategoryDelete
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(
        @RequestParam("name") nameQuery: String? = null
    ) = findAll(nameQuery).map { it.toResponse() }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOne(
        @PathVariable("id") categoryId: Long
    ) = findOne(categoryId).getOrThrow().toResponse()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun post(
        @RequestBody request: CategoryCreateRequest
    ) = create(request.toEntity()).getOrThrow().toResponse()

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun del(
        @PathVariable("id") categoryId: Long
    ) = delete(categoryId)
}