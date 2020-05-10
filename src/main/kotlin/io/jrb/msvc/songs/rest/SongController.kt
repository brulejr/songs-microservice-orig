package io.jrb.msvc.songs.rest

import io.jrb.msvc.songs.resource.Song
import io.jrb.msvc.songs.service.SongService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/songs")
class SongController(val songService: SongService) {

    @GetMapping
    fun listSongs() = songService.listSongs()

    @GetMapping("/{songGuid}")
    fun getSongById(@PathVariable songGuid: String) = songService.findSong(songGuid)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSong(@RequestBody song: Song) = songService.createSong(song)

    @DeleteMapping("/{songGuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSong(@PathVariable songGuid: String) = songService.deleteSong(songGuid)

}
