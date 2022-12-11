package br.com.redosul.server.category.type

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CategoryClousureId(
    @Column var parentId: Long = 0,
    @Column var childId: Long = 0,
): Serializable {
    companion object {
        val ZERO = CategoryClousureId(0, 0)
    }
}