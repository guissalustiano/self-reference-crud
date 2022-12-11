package br.com.redosul.server.category.message

import br.com.redosul.server.category.Category
import java.time.ZonedDateTime

data class CategoryResponse(
    val parentId: Long?,
    val id: Long,
    val code: String,
    val name: String,
    val description: String?,
    val slug: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Category.toResponse() = CategoryResponse(
    parent?.getId()?.value,
    getId().value,
    code.value,
    name.value,
    description?.value,
    slug.value,
    createdAt,
    updatedAt,
)
