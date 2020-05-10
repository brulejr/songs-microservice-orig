package io.jrb.msvc.songs.repository

import io.jrb.msvc.songs.model.Song
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface SongRepository : ReactiveCrudRepository<Song, Long> {

}
