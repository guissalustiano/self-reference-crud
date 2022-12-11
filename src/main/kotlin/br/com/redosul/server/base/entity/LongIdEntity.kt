package br.com.redosul.server.base.entity

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils

@MappedSuperclass
abstract class LongIdEntity(id: Long) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected var id: Long = id

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as LongIdEntity

        return if (!isPersisted()) false else this.id == other.id
    }

    override fun isPersisted() = this.id != 0L

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}