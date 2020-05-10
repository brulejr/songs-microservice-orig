package io.jrb.msvc.songs.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table("song")
data class Song(
        @Id val id: Long?,
        @Column("title") val title: String
) { }
