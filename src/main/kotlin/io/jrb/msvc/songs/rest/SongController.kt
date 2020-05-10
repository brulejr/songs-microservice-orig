package io.jrb.msvc.songs.rest

import io.jrb.msvc.songs.model.Song
import io.jrb.msvc.songs.repository.SongRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/songs")
class SongController(val repo: SongRepository) {

    @GetMapping
    fun listSongs() = repo.findAll()

    @GetMapping("/{songId}")
    fun getSongById(@PathVariable songId: Long) = repo.findById(songId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody song: Song) = repo.save(song)

    @DeleteMapping("/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun save(@PathVariable songId: Long) = repo.deleteById(songId)

}
