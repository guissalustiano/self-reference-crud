package br.com.redosul.server.category.message

import br.com.redosul.server.base.extension.toSlug
import br.com.redosul.server.category.Category

data class CategoryCreateRequest(
    val code: String,
    val name: String,
    val description: String?,
    val slug: String = name.toSlug(),
)

fun CategoryCreateRequest.toEntity() = Category(
    code,
    name,
    description,
    slug,
)
