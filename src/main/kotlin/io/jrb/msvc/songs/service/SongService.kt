package io.jrb.msvc.songs.service

import io.jrb.msvc.songs.model.SongEntity
import io.jrb.msvc.songs.repository.SongEntityRepository
import io.jrb.msvc.songs.resource.Song
import io.jrb.msvc.songs.resource.SongMetadata
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class SongService(val songEntityRepository: SongEntityRepository) {

    private val log = KotlinLogging.logger {}

    fun createSong(song: Song): Mono<Song> {
        val songEntity = SongEntity.fromSong(song)
        return songEntityRepository.save(songEntity)
                .map { SongEntity.toSong(it) }
                .onErrorResume(serviceErrorHandler("Unexpected error when creating song"))
    }

    fun deleteSong(songGuid: String): Mono<Void> {
        return songEntityRepository.findSongByGuid(songGuid)
                .switchIfEmpty(Mono.error(ResourceNotFoundException("Song", songGuid)))
                .flatMap(songEntityRepository::delete)
                .onErrorResume(serviceErrorHandler("Unexpected error when deleting song"))
    }

    fun findSong(songGuid: String): Mono<Song> {
        return songEntityRepository.findSongByGuid(songGuid)
                .switchIfEmpty(Mono.error(ResourceNotFoundException("Song", songGuid)))
                .map { SongEntity.toSong(it) }
                .onErrorResume(serviceErrorHandler("Unexpected error when finding song"))
    }

    fun listSongs(): Flux<SongMetadata> {
        return songEntityRepository.findAll()
                .map { SongEntity.toSongMetadata(it) }
                .onErrorResume(serviceErrorHandler("Unexpected error when retrieving songs"))
    }

    private fun <R> serviceErrorHandler(message: String): (Throwable) -> Mono<R> {
        return { e ->
            if (e !is ServiceException) {
                log.error(e.message, e)
            }
            Mono.error(if (e is ServiceException) e else ServiceException(message, e))
        }
    }

}
