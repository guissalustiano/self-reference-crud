package br.com.redosul.server.base.entity

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils
import java.io.Serializable

@MappedSuperclass
abstract class EmbbededIdEntity<T: Serializable>(id: T) : BaseEntity() {
    @EmbeddedId
    protected var id: T = id

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as EmbbededIdEntity<*>

        return if (null == this.id) false else this.id == other.id
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}