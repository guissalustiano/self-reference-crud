package br.com.redosul.server.category.type

@JvmInline
value class CategoryId(val value: Long) {
    companion object {
        val ZERO = CategoryId(0)
    }
}
