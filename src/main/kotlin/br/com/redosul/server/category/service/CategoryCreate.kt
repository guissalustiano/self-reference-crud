package br.com.redosul.server.category.service

import br.com.redosul.server.category.message.CategoryCreatePayload
import br.com.redosul.server.category.message.toIdAndCategory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryCreate(
    private val save: CategorySave,
    private val categoryFindOne: CategoryFindOne,
) {
    operator fun invoke(
        payload: CategoryCreatePayload,
    ) = runCatching {
        val (parentId, children) =  payload.toIdAndCategory()
        val parent = parentId?.let { categoryFindOne(it).getOrThrow() }
        parent?.addChildren(children)
        save(children).getOrThrow()
    }
}