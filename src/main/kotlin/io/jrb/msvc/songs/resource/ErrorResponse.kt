package io.jrb.msvc.songs.resource

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.Instant

data class ErrorResponse(
        val status: HttpStatus,
        val code: Int,
        val message: String?,
        val timestamp: Instant,
        val bindingErrors: List<String>
) {

    constructor(status: HttpStatus, message: String?, bindingErrors: List<String>) :
            this(status, status.value(), message, Instant.now(), bindingErrors)

    constructor(status: HttpStatus, message: String?) :
            this(status, status.value(), message, Instant.now(), ArrayList<String>())

}

class ErrorResponseEntity(body: ErrorResponse) : ResponseEntity<ErrorResponse>(body, body.status) {

    companion object {

        fun badRequest(message: String?) =
                ErrorResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST, message))

        fun badRequest(message: String?, bindingErrors: List<String>) =
                ErrorResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST, message, bindingErrors))

        fun notFound(message: String?) =
                ErrorResponseEntity(ErrorResponse(HttpStatus.NOT_FOUND, message))

        fun serverError(message: String?) =
                ErrorResponseEntity(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message))

    }

}
