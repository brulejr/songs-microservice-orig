package io.jrb.msvc.songs.model

import io.jrb.msvc.songs.resource.Song
import io.jrb.msvc.songs.resource.SongMetadata
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class SongEntity(
        @Id val id: String?,
        val guid: String?,
        val title: String,
        val authors: List<String>,
        val additionalTitles: List<String>,
        val themes: List<String>,
        val lyrics: Map<String, List<String>>,
        val lyricOrder: List<String>
) {

    companion object {

        @JvmStatic
        fun fromSong(song: Song): SongEntity = SongEntity(
                id = null,
                guid = song.guid ?: UUID.randomUUID().toString(),
                title = song.title,
                authors = song.authors,
                additionalTitles = song.additionalTitles,
                themes = song.themes,
                lyrics = song.lyrics,
                lyricOrder = song.lyricOrder
        )

        @JvmStatic
        fun toSong(songEntity: SongEntity): Song = Song(
                guid = songEntity.guid,
                title = songEntity.title,
                authors = songEntity.authors,
                additionalTitles = songEntity.additionalTitles,
                themes = songEntity.themes,
                lyrics = songEntity.lyrics,
                lyricOrder = songEntity.lyricOrder
        )

        @JvmStatic
        fun toSongMetadata(songEntity: SongEntity): SongMetadata = SongMetadata(
                guid = songEntity.guid,
                title = songEntity.title
        )

    }

}
