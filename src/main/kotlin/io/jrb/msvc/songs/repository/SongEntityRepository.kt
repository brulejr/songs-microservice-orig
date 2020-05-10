package io.jrb.msvc.songs.repository

import io.jrb.msvc.songs.model.SongEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface SongEntityRepository : ReactiveCrudRepository<SongEntity, Long> {

    @Query("select s.* from Song s where s.guid = :guid")
    fun findSongByGuid(guid: String): Mono<SongEntity>

}
