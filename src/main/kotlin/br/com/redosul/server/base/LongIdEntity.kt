package br.com.redosul.server.base

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils

@MappedSuperclass
abstract class LongIdEntity(id: Long) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

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