package br.com.redosul.server.base

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.ZonedDateTime

@MappedSuperclass
//@TypeDefs(TypeDef(name = "jsonb", typeClass = JsonBinaryType::class))
abstract class BaseEntity<T: Serializable>(id: T? = null) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = id
        protected set

    //    @NotAudited
    @org.hibernate.annotations.CreationTimestamp
    var createdAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    //    @NotAudited
    @org.hibernate.annotations.UpdateTimestamp
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return if (null == this.id) false else this.id == other.id
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}