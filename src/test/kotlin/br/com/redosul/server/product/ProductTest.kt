package br.com.redosul.server.product

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Description
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.category.CategoryFake
import br.com.redosul.server.util.context
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class ProductTest : FunSpec({
    context(Product) {
        test("should link category to product") {
            val category = CategoryFake.default()
            val product = Product(
                code = Code("P1").getOrThrow(),
                name = Name("Product 1").getOrThrow(),
                description = Description("Product 1 description"),
                category = category
            )

            product.category shouldBe category
            category.products shouldContain product
        }
    }

})
