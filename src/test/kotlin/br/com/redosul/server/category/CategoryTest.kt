package br.com.redosul.server.category

import br.com.redosul.server.base.type.Code
import br.com.redosul.server.base.type.Name
import br.com.redosul.server.category.type.CategoryId
import br.com.redosul.server.product.ProductFake
import br.com.redosul.server.util.context
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CategoryTest : FunSpec({
    context(Category) {
        test("should create a base Category by only invoking the constructor") {
            val category = Category(
                code = Code("A1").getOrThrow(),
                name = Name("category").getOrThrow(),
                description = null,
            )

            category.getId() shouldBe CategoryId.ZERO
            category.depth shouldBe 0u
            category.parent shouldBe null
            category.children shouldBe emptySet()
        }
    }

    context(Category::addChildren) {
        test("should add a child to a category that change the depth and and parents") {
            val parent = CategoryFake.default(id=1)
            val child = CategoryFake.default(id=2)

            parent.addChildren(child)

            child.depth shouldBe 1u
            child.parent shouldBe parent
            parent.children shouldBe setOf(child)

            // absurd test
            parent.depth shouldBe 0u
            parent.parent shouldBe null
            child.children shouldBe emptySet()
        }

        test("should could returns all parents/children") {
            val parent = CategoryFake.default(id=1)
            val child = CategoryFake.default(id=2)
            val grandChild = CategoryFake.default(id=3)

            parent.addChildren(child)
            child.addChildren(grandChild)

            parent.depth shouldBe 0u
            child.depth shouldBe 1u
            grandChild.depth shouldBe 2u

            grandChild.allParents shouldBe setOf(parent, child)
            parent.allChildrens shouldBe setOf(child, grandChild)

            // absurd test
            parent.allParents shouldBe emptySet()
            grandChild.allChildrens shouldBe emptySet()
        }
    }
})

