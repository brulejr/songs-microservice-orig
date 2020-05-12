package io.jrb.msvc.songs.rest

import com.github.fge.jsonpatch.JsonPatch
import io.jrb.msvc.songs.resource.Song
import io.jrb.msvc.songs.service.SongService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/songs")
class SongController(val songService: SongService) {

    @GetMapping
    fun listSongs() = songService.listSongs()

    @GetMapping("/{songGuid}")
    fun getSongById(@PathVariable songGuid: String)
            = songService.findSong(songGuid)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSong(@RequestBody song: Song)
            = songService.createSong(song)

    @DeleteMapping("/{songGuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSong(@PathVariable songGuid: String)
            = songService.deleteSong(songGuid)

    @PatchMapping(path = ["/{songGuid}"], consumes = ["application/json-patch+json"])
    fun patchSong(@PathVariable songGuid: String, @RequestBody songPatch: JsonPatch)
            = songService.updateSong(songGuid, songPatch)

}
