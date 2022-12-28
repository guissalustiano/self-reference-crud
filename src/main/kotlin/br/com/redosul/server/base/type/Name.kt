package br.com.redosul.server.base.type

@JvmInline
value class Name(val value: String): Comparable<String> {
    override fun compareTo(other: String): Int = value.compareTo(other)
}
