package br.com.redosul.server.base.type

@JvmInline
value class Code private constructor(val value: String): Comparable<String> {
    init {
        require(value.matches(Regex("^[A-Z]+[0-9]+\$"))) { "Code must start with letters and end with numbers" }
    }

    override fun compareTo(other: String): Int = value.compareTo(other)

    companion object {
        operator fun invoke(value: String) = runCatching {
            Code(value)
        }
    }
}
