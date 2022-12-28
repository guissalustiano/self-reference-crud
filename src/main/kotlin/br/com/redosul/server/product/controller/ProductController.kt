package br.com.redosul.server.product.controller

import br.com.redosul.server.category.message.toCategoryWithChildrenResponse
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import br.com.redosul.server.product.message.ProductCreatePayload
import br.com.redosul.server.product.message.toProductResponse
import br.com.redosul.server.product.service.ProductCreate
import br.com.redosul.server.product.service.ProductFindAll
import br.com.redosul.server.product.service.ProductFindOne
import br.com.redosul.server.product.type.ProductId
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val productCreate: ProductCreate,
    private val productFindAll: ProductFindAll,
    private val productFindOne: ProductFindOne,
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(
        @RequestParam("categoryId") categoryId: Long?
    )= productFindAll(categoryId?.let { CategoryId(it) }).map { it.toProductResponse() }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOne(
        @PathVariable("id") productId: Long
    ) = productFindOne(ProductId(productId))?.toProductResponse()

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun post(
        @RequestBody payload: ProductCreatePayload
    ) = productCreate(payload).toProductResponse()
}