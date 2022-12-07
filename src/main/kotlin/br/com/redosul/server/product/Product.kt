package br.com.redosul.server.product

import br.com.redosul.server.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.NaturalId

@Entity
class Product(
    @NaturalId var code: String,
    @Column  var name: String,
    @Column var description: String?,
    id: Long? = null,
): BaseEntity<Long>(id)