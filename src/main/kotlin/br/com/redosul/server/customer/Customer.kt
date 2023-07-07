package br.com.redosul.server.customer

import br.com.redosul.server.base.entity.LongIdEntity
import br.com.redosul.server.category.type.CategoryId
import jakarta.persistence.Column

class Customer(
    @Column var email: String,
    id: CategoryId = CategoryId.ZERO,
): LongIdEntity(id.value)