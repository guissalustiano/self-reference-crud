package br.com.redosul.server.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.scopes.FunSpecContainerScope



fun FunSpec.context(
    func: Function<Unit>,
    test: suspend FunSpecContainerScope.() -> Unit
) {
    // expected <Path>.<Class>.<method>(...)
    // then get Class.method
    val classAndMethodPattern = "(\\w+\\.\\w+)\\(".toRegex()

    val name = classAndMethodPattern.find(func.toString())!!.groupValues[1].replace(".", "::")
    return context(name, test)
}

fun FunSpec.context(
    companion: Any,
    test: suspend FunSpecContainerScope.() -> Unit
) {
    // expected <Path>.<Class>$Companion
    // then get Class.method
    val classPattern = "\\w+\\.(\\w+)\\\$Companion".toRegex()
    val name = classPattern.find(companion.toString())!!.groupValues[1] + "::contructor"

    return context(name, test)
}