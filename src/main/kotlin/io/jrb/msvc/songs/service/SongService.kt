package io.jrb.msvc.songs.service

import io.jrb.msvc.songs.model.SongEntity
import io.jrb.msvc.songs.repository.SongEntityRepository
import io.jrb.msvc.songs.resource.Song
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class SongService(val songEntityRepository: SongEntityRepository) {

    fun createSong(song: Song): Mono<Song> {
        val songEntity = SongEntity.fromSong(song)
        return songEntityRepository.save(songEntity)
                .map { Song.fromSongEntity(it) }
    }

    fun deleteSong(songGuid: String): Mono<Void> {
        return songEntityRepository.findSongByGuid(songGuid)
                .flatMap(songEntityRepository::delete)
    }

    fun findSong(songGuid: String): Mono<Song> {
        return songEntityRepository.findSongByGuid(songGuid)
                .map { Song.fromSongEntity(it) }
    }

    fun listSongs(): Flux<Song> {
        return songEntityRepository.findAll()
                .map { Song.fromSongEntity(it) }
    }

}
