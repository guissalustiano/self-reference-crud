package br.com.redosul.server.category.message

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.base.type.Slug
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.type.CategoryId
import java.time.ZonedDateTime

data class CategoryWithChildrenResponse(
    val parentId: CategoryId?,
    val id: CategoryId,
    val code: Code,
    val name: Name,
    val description: Description?,
    val children: List<CategoryResponse>,
    val depth: UInt,
    val slug: Slug,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Category.toCategoryWithChildrenResponse() = CategoryWithChildrenResponse(
    parent?.getId(),
    getId(),
    code,
    name,
    description,
    children.map { it.toCategoryResponse() },
    depth,
    slug,
    createdAt,
    updatedAt,
)
