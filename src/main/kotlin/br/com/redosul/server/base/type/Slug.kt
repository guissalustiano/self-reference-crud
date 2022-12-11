package br.com.redosul.server.base.type

import java.util.*

@JvmInline
value class Slug private constructor(val value: String): Comparable<String> {
    init {
        require(value.matches(Regex("^[a-z0-9-]+\$"))) { "Slug must contain only lower letters, numbers and hyphens" }
    }

    override fun compareTo(other: String): Int = value.compareTo(other)

    companion object {
        operator fun invoke(value: String) = runCatching {
            Slug(value)
        }
    }
}


fun Name.toSlug() = Slug(
    value.lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
).getOrThrow()
