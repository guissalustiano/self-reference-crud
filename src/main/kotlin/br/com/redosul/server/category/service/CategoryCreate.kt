package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.CategoryClousure
import br.com.redosul.server.category.exception.CategoryException
import br.com.redosul.server.category.repository.CategoryClousureRepository
import br.com.redosul.server.category.type.CategoryId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryCreate(
    private val categorySave: CategorySave,
    private val categoryFindOne: CategoryFindOne,
    private val clousureRepository: CategoryClousureRepository,
) {
    operator fun invoke(
        parentId: CategoryId?,
        category: Category
    )= runCatching {
        val parent = parentId?.let { categoryFindOne(it).getOrThrow() }
        val newNodeDepth = parent?.let{it.depth + 1u} ?: 0u

        val child = categorySave(category).getOrThrow()
        val relations = setKinship(child, parent)

        clousureRepository.saveAll(relations)

        if (child.depth >= 3u) {
            throw CategoryException.TooDepth(newNodeDepth)
        }

        child
    }

    private fun setKinship(
        child: Category,
        parent: Category?
    ): List<CategoryClousure> {
        val parentReference = parent?.parentConnections?.map {
            CategoryClousure(
                it.parent,
                child,
                it.depth + 1u
            )
        }.orEmpty()

        val selfReference = CategoryClousure(
            child,
            child,
            parentReference.firstOrNull()?.depth ?: 0u
        )

        val references = listOf(selfReference) + parentReference

        return references.onEach {
            parent?.addChildConnection(it)
            child.addParentConnection(it)
        }.toList()
    }
}