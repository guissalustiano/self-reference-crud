package br.com.redosul.server.category.message

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.base.type.Slug
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.type.CategoryId
import java.time.ZonedDateTime

data class CategoryResponse(
    val parentId: CategoryId?,
    val id: CategoryId,
    val code: Code,
    val name: Name,
    val description: Description?,
    val depth: UInt,
    val slug: Slug,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

fun Category.toCategoryResponse() = CategoryResponse(
    parent?.getId(),
    getId(),
    code,
    name,
    description,
    depth,
    slug,
    createdAt,
    updatedAt,
)
