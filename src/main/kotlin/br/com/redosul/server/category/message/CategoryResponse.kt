package br.com.redosul.server.category.message

import br.com.redosul.server.category.Category

data class CategoryResponse(
    val id: Long,
    val code: String,
    val name: String,
    val description: String?,
    val slug: String,
)

fun Category.toResponse() = CategoryResponse(
    id,
    code,
    name,
    description,
    slug,
)
