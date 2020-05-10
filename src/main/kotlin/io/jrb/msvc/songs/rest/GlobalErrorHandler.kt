package io.jrb.msvc.songs.rest

import io.jrb.msvc.songs.resource.ErrorResponseEntity
import io.jrb.msvc.songs.service.ResourceNotFoundException
import io.jrb.msvc.songs.service.ServiceException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun forumException(exception: ResourceNotFoundException) = ErrorResponseEntity.notFound(exception.message)

    @ExceptionHandler(ServiceException::class)
    fun forumException(exception: ServiceException) = ErrorResponseEntity.serverError(exception.message)
    
}
