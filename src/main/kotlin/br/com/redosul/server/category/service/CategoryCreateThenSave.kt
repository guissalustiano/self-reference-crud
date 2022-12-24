package br.com.redosul.server.category.service

import br.com.redosul.server.category.Category
import br.com.redosul.server.category.message.CategoryCreatePayload
import br.com.redosul.server.category.message.toIdAndCategory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryCreateThenSave(
    private val create: CategoryCreate,
    private val save: CategorySave,
    private val categoryFindOne: CategoryFindOne,
) {
    operator fun invoke(
        payload: CategoryCreatePayload,
    ) = runCatching {
        val (parentId, children) =  payload.toIdAndCategory()
        val parent = parentId?.let { categoryFindOne(it).getOrThrow() }
        create(parent, children).getOrThrow()
    }.mapCatching {
        save(it.first, it.second).getOrThrow()
    }
}