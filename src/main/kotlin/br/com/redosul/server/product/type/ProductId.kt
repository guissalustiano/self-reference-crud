package br.com.redosul.server.product.type

inline class ProductId(val value: Long) {
    companion object {
        val ZERO = ProductId(0)
    }
}
