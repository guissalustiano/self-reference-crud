package br.com.redosul.server.product.message

import br.com.redosul.server.base.type.*
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.Product
import com.fasterxml.jackson.annotation.JsonCreator

data class ProductCreatePayload(
    val categoryId: CategoryId,
    val code: Code,
    val name: Name,
    val description: Description?,
    val slug: Slug,
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun fromJson(
            categoryId: Long?,
            code: String?,
            name: String?,
            description: String?,
            slug: String?, // TODO: undefinable
        ): ProductCreatePayload {
            val tName = name?.let { Name(it) } ?: throw CategoryException.Required.Name
            return ProductCreatePayload(
                categoryId?.let { CategoryId(it) } ?: throw CategoryException.NotFound(CategoryId.ZERO),
                code?.let { Code(it) } ?: throw CategoryException.Required.Code,
                tName,
                description?.let { Description(it) },
                slug?.let { Slug(it) } ?: tName.toSlug(),
            )
        }
    }
}

fun ProductCreatePayload.toProduct(load: (CategoryId) -> Category) = Product(
    code = code,
    name = name,
    description = description,
    category = load(categoryId),
    slug = slug,
)