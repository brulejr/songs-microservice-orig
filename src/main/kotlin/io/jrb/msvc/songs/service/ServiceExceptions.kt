package io.jrb.msvc.songs.service

open class ServiceException(message: String, cause: Throwable?): Exception(message, cause)

class PatchInvalidException(guid: String?, message: String?): ServiceException(
        message = "Invalid patch for resource - guid = [$guid], detail = $message",
        cause = null
)

class ResourceNotFoundException(type: String, guid: String): ServiceException(
        message = "No $type resource can be found - guid = [$guid]",
        cause = null
)
