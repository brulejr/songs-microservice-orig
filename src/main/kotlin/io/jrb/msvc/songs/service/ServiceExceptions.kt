package io.jrb.msvc.songs.service

open class ServiceException(message: String, cause: Throwable?): Exception(message, cause) { }

class PatchInvalidException(id: String): ServiceException(
        message = "Invalid patch for resource under the [$id] identifier",
        cause = null
) { }

class ResourceNotFoundException(type: String, id: String): ServiceException(
        message = "No $type resource can be found under the [$id] identifier",
        cause = null
) { }
