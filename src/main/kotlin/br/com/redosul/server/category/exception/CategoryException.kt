package br.com.redosul.server.category.exception

sealed class CategoryException: Exception() {
    data class NotFound(val id: Long) : CategoryException()
    data class AlreadyExistCode(val name: String) : CategoryException()
    data class AlreadyExistsName(val name: String) : CategoryException()
    data class AlreadyExistsSlug(val slug: String) : CategoryException()
}
