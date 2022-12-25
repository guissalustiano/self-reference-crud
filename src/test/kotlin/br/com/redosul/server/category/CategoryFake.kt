package br.com.redosul.server.category

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.category.type.CategoryId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

object CategoryFake {
    fun default(
        code: String = "T1",
        name: String = "Teste Category",
        description: String? = "Teste description",
        id : Long = 1L,
    ) = Category(
        code = Code(code).getOrThrow(),
        name = Name(name).getOrThrow(),
        description = description?.let { Description(it) },
        id = CategoryId(id)
    )
}
