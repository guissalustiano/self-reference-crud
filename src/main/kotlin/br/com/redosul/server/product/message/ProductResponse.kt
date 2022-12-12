package br.com.redosul.server.product.message

import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import br.com.redosul.server.product.type.ProductId
import java.time.ZonedDateTime

data class ProductResponse(
    val categoryId: CategoryId,
    val id: ProductId,
    val code: Code,
    val name: Name,
    val description: Description?,
    val slug: Slug,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Product.toProductResponse() = ProductResponse(
    category.getId(),
    getId(),
    code,
    name,
    description,
    slug,
    createdAt,
    updatedAt,
)