package br.com.redosul.server.base.type

@JvmInline
value class Name private constructor(val value: String): Comparable<String> {
    init {
        require(value.matches(Regex("^[A-Za-z0-9 -]+\$"))) { "Name must contain only letters, numbers, spaces and hyphens" }
    }

    override fun compareTo(other: String): Int = value.compareTo(other)

    companion object {
        operator fun invoke(value: String) = runCatching {
            Name(value)
        }
    }
}
