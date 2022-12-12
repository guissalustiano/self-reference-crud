package br.com.redosul.server.category.message

import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.type.CategoryId
import com.fasterxml.jackson.annotation.JsonCreator

data class CategoryCreatePayload(
    val parentId: CategoryId?,
    val code: Code,
    val name: Name,
    val description: Description?,
    val slug: Slug = name.toSlug(),
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun fromJson(
            parentId: Long?,
            code: String?,
            name: String?,
            description: String?,
            slug: String?, // TODO: undefinable
        ): CategoryCreatePayload {
            val tName = name?.let { Name(it).getOrThrow() } ?: throw CategoryException.Required.Name
            return CategoryCreatePayload(
                parentId?.let { CategoryId(it) },
                code?.let { Code(it).getOrThrow() } ?: throw CategoryException.Required.Code,
                tName,
                description?.let { Description(it) },
                slug?.let { Slug(it).getOrThrow() } ?: tName.toSlug(),
            )
        }
    }
}

fun CategoryCreatePayload.toIdAndCategory() = parentId to Category(
    code,
    name,
    description,
    slug,
)
