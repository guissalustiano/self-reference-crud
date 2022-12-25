package br.com.redosul.server.util

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class SpringFunSpec : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}