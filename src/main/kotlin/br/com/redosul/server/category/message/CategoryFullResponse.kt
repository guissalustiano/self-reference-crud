package br.com.redosul.server.category.message

import br.com.redosul.server.category.Category
import java.time.ZonedDateTime

data class CategoryWithChildrenResponse(
    val parentId: Long?,
    val id: Long,
    val code: String,
    val name: String,
    val description: String?,
    val children: List<CategoryResponse>,
    val depth: Int,
    val slug: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Category.toCategoryWithChildrenResponse() = CategoryWithChildrenResponse(
    directParent?.getId()?.value,
    getId().value,
    code.value,
    name.value,
    description?.value,
    directChildren.map { it.toCategoryResponse() },
    depth.toInt(),
    slug.value,
    createdAt,
    updatedAt,
)
