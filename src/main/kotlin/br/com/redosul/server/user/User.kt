package br.com.redosul.server.user

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
open class User(
    @Column var email: String,
    id: CategoryId = CategoryId.ZERO,
): LongIdEntity(id.value)