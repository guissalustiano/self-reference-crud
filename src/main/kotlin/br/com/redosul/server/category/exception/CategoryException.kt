package br.com.redosul.server.category.exception

import br.com.redosul.server.base.exception.AppException
import br.com.redosul.server.category.type.CategoryId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import br.com.redosul.server.base.type.Code as TCode
import br.com.redosul.server.base.type.Name as TName
import br.com.redosul.server.base.type.Slug as TSlug

sealed class CategoryException(message: String): AppException(message){
    class NotFound(id: CategoryId) : CategoryException(
        "Category not found: $id"
    )

    class TooDepth(depth: UInt) : CategoryException(
        "Category depth is too deep: $depth"
    )

    sealed class Required(message: String): CategoryException(message) {
        object Code : Required(
            "Category code is required"
        )
        object Name : Required(
            "Category name is required"
        )
    }

    sealed class AlreadyExist(message: String) : CategoryException(message) {
        data class Code(val name: TCode): AlreadyExist(
            "Category code already exists: $name"
        )
        data class Name(val name: TName): AlreadyExist(
            "Category name already exists: $name"
        )
        data class Slug(val name: TSlug): AlreadyExist(
            "Category slug already exists: $name"
        )
    }
}
