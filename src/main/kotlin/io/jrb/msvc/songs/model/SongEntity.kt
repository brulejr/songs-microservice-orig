package io.jrb.msvc.songs.model

import io.jrb.msvc.songs.resource.Song
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("song")
data class SongEntity(
        @Id val id: Long?,
        @Column("guid") val guid: String,
        @Column("title") val title: String
) {

    companion object {

        @JvmStatic
        fun fromSong(song: Song): SongEntity = SongEntity(
                id = null,
                guid = song.guid ?: UUID.randomUUID().toString(),
                title = song.title
        )

    }

}
