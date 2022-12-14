package br.com.redosul.server.category.controller

import br.com.redosul.server.category.message.CategoryCreatePayload
import br.com.redosul.server.category.message.toCategoryResponse
import br.com.redosul.server.category.message.toCategoryWithChildrenResponse
import br.com.redosul.server.category.service.*
import br.com.redosul.server.category.type.CategoryId
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val findOne: CategoryFindOne,
    private val findAll: CategoryFindAll,
    private val create: CategoryCreate,
    private val delete: CategoryDelete
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get() = findAll().map { it.toCategoryResponse() }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOne(
        @PathVariable("id") categoryId: Long
    ) = findOne(CategoryId(categoryId)).toCategoryWithChildrenResponse()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun post(
        @RequestBody payload: CategoryCreatePayload
    ) = create(payload).toCategoryResponse()

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun del(
        @PathVariable("id") categoryId: Long
    ) = delete(CategoryId(categoryId))
}