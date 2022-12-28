package br.com.redosul.server.base.message

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.time.LocalDateTime


class ApiError(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val message: String = "Unexpected error",

) {
    constructor(status: HttpStatus, ex: Throwable, message: String? = null) : this(
        status,
        message = message ?: ex.localizedMessage ?: "",
    )
}