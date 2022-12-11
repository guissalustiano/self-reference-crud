package br.com.redosul.server.base

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.ZonedDateTime

@MappedSuperclass
//@TypeDefs(TypeDef(name = "jsonb", typeClass = JsonBinaryType::class))
abstract class BaseEntity() : Serializable {
    abstract fun isPersisted(): Boolean
    //    @NotAudited
    @org.hibernate.annotations.CreationTimestamp
    var createdAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    //    @NotAudited
    @org.hibernate.annotations.UpdateTimestamp
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
        protected set
}