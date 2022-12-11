package br.com.redosul.server.category.exception

import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.base.type.Code as TCode
import br.com.redosul.server.base.type.Name as TName
import br.com.redosul.server.base.type.Slug as TSlug

sealed class CategoryException: Exception() {
    class TooDepth(val depth: UInt) : CategoryException()

    data class NotFound(val id: CategoryId) : CategoryException()

    sealed class Required: CategoryException() {
        object Code : Required()
        object Name : Required()
    }

    sealed class AlreadyExist : CategoryException() {
        data class Code(val name: TCode): AlreadyExist()
        data class Name(val name: TName): AlreadyExist()
        data class Slug(val name: TSlug): AlreadyExist()
    }

}
