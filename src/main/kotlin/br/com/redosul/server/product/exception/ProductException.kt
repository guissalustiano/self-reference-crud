package br.com.redosul.server.product.exception

import br.com.redosul.server.base.exception.AppException
import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.type.ProductId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import br.com.redosul.server.base.type.Code as TCode
import br.com.redosul.server.base.type.Name as TName
import br.com.redosul.server.base.type.Slug as TSlug

sealed class ProductException(message: String): AppException(message) {
    data class NotFound(val id: ProductId) : ProductException(
        "Product not found: $id"
    )

    sealed class Required(message: String): ProductException(message) {
        object Code : Required(
            "Product code is required"
        )
        object Name : Required(
            "Product name is required"
        )
    }

    sealed class AlreadyExist(message: String) : ProductException(message) {
        data class Code(val name: TCode): AlreadyExist(
            "Product code already exists: $name"
        )
        data class Name(val name: TName): AlreadyExist(
            "Product name already exists: $name"
        )
        data class Slug(val name: TSlug): AlreadyExist(
            "Product slug already exists: $name"
        )
    }
}
