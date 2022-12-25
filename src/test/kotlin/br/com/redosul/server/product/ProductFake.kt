package br.com.redosul.server.product

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryFake
import br.com.redosul.server.product.type.ProductId

object ProductFake {
    fun default(
        code: String = "T1",
        name: String = "Teste Product",
        description: String? = "Teste description",
        category: Category = CategoryFake.default(),
        id : Long = 1L,
    ) = Product(
        code = Code(code).getOrThrow(),
        name = Name(name).getOrThrow(),
        description = description?.let { Description(it) },
        category = category,
        id = ProductId(id)
    )
}
