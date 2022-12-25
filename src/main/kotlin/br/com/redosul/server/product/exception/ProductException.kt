package br.com.redosul.server.product.exception

import br.com.redosul.server.product.type.ProductId
import br.com.redosul.server.base.type.Code as TCode
import br.com.redosul.server.base.type.Name as TName
import br.com.redosul.server.base.type.Slug as TSlug

sealed class ProductException: Exception() {
    data class NotFound(val id: ProductId) : ProductException()
    sealed class Required: ProductException() {
        object Code : Required()
        object Name : Required()
    }

    sealed class AlreadyExist : ProductException() {
        data class Code(val name: TCode): AlreadyExist()
        data class Name(val name: TName): AlreadyExist()
        data class Slug(val name: TSlug): AlreadyExist()
    }
}
