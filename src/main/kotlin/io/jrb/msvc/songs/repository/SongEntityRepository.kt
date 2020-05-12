package io.jrb.msvc.songs.repository

import io.jrb.msvc.songs.model.SongEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface SongEntityRepository : ReactiveMongoRepository<SongEntity, Long> {

    fun findSongByGuid(title: String): Mono<SongEntity>

}
