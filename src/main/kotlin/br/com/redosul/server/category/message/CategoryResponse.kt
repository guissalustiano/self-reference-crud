package br.com.redosul.server.category.message

import br.com.redosul.server.category.Category
import java.time.ZonedDateTime

data class CategoryResponse(
    val parentId: Long?,
    val id: Long,
    val code: String,
    val name: String,
    val description: String?,
    val depth: Int,
    val slug: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Category.toCategoryResponse() = CategoryResponse(
    directParent?.getId()?.value,
    getId().value,
    code.value,
    name.value,
    description?.value,
    depth.toInt(),
    slug.value,
    createdAt,
    updatedAt,
)
