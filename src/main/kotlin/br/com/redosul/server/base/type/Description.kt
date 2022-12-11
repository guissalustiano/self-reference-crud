package br.com.redosul.server.base.type

@JvmInline
value class Description(val value: String): Comparable<String> {
    override fun compareTo(other: String): Int = value.compareTo(other)
}
