package io.jrb.msvc.songs.resource

import io.jrb.msvc.songs.model.SongEntity

data class Song(
        val guid: String?,
        val title: String
) {

    companion object {

        @JvmStatic
        fun fromSongEntity(songEntity: SongEntity): Song = Song(
                guid = songEntity.guid,
                title = songEntity.title
        )

    }

}
