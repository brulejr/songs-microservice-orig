package io.jrb.msvc.songs.model

import io.jrb.common.test.Testable
import io.jrb.msvc.songs.resource.Song
import io.jrb.msvc.songs.resource.SongMetadata
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils.nextInt
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SongEntityTest : Testable {

    lateinit var id: String
    lateinit var guid: String
    lateinit var title: String
    lateinit var authors: List<String>
    lateinit var additionalTitles: List<String>
    lateinit var themes: List<String>
    lateinit var lyrics: Map<String, List<String>>
    lateinit var lyricOrder: List<String>
    lateinit var sourceId: String
    lateinit var sourceSystem: String
    lateinit var songEntity: SongEntity

    @BeforeEach
    fun before() {
        id = randomAlphabetic(10, 25)
        guid = randomAlphabetic(10, 25)
        title = randomAlphabetic(10, 25)
        authors = randomStringList(nextInt(3, 5))
        additionalTitles = randomStringList(nextInt(3, 5))
        themes = randomStringList(nextInt(3, 5))
        lyrics = buildMap(
                nextInt(3, 5),
                { i -> randomAlphabetic(10, 25) },
                { i -> randomStringList(nextInt(3, 5)) }
        )
        lyricOrder = randomStringList(nextInt(3, 5))
        sourceId = randomAlphabetic(10, 25)
        sourceSystem = randomAlphabetic(10, 25)
        songEntity = SongEntity(
                id = id,
                guid = guid,
                title = title,
                authors = authors,
                additionalTitles = additionalTitles,
                themes = themes,
                lyrics = lyrics,
                lyricOrder = lyricOrder,
                sourceId = sourceId,
                sourceSystem = sourceSystem
        )
    }

    @Test
    fun testCreate() {
        assertThat(songEntity.id, equalTo(id))
        assertThat(songEntity.guid, equalTo(guid))
        assertThat(songEntity.title, equalTo(title))
        assertThat(songEntity.authors, equalTo(authors))
        assertThat(songEntity.additionalTitles, equalTo(additionalTitles))
        assertThat(songEntity.themes, equalTo(themes))
        assertThat(songEntity.lyrics, equalTo(lyrics))
        assertThat(songEntity.lyricOrder, equalTo(lyricOrder))
        assertThat(songEntity.sourceId, equalTo(sourceId))
        assertThat(songEntity.sourceSystem, equalTo(sourceSystem))
    }

    @Test
    fun testFromSong() {
        val song = Song(
                guid = guid,
                title = title,
                authors = authors,
                additionalTitles = additionalTitles,
                themes = themes,
                lyrics = lyrics,
                lyricOrder = lyricOrder,
                sourceId = sourceId,
                sourceSystem = sourceSystem
        )
        val songEntity: SongEntity = SongEntity.fromSong(song)
        assertThat(songEntity.id, nullValue())
        assertThat(songEntity.guid, equalTo(guid))
        assertThat(songEntity.title, equalTo(title))
        assertThat(songEntity.authors, equalTo(authors))
        assertThat(songEntity.additionalTitles, equalTo(additionalTitles))
        assertThat(songEntity.themes, equalTo(themes))
        assertThat(songEntity.lyrics, equalTo(lyrics))
        assertThat(songEntity.lyricOrder, equalTo(lyricOrder))
        assertThat(songEntity.sourceId, equalTo(sourceId))
        assertThat(songEntity.sourceSystem, equalTo(sourceSystem))
    }

    @Test
    fun testFromSongWithId() {
        val song = Song(
                guid = guid,
                title = title,
                authors = authors,
                additionalTitles = additionalTitles,
                themes = themes,
                lyrics = lyrics,
                lyricOrder = lyricOrder,
                sourceId = sourceId,
                sourceSystem = sourceSystem
        )
        val songEntity: SongEntity = SongEntity.fromSong(song, id)
        assertThat(songEntity.id, equalTo(id))
        assertThat(songEntity.guid, equalTo(guid))
        assertThat(songEntity.title, equalTo(title))
        assertThat(songEntity.authors, equalTo(authors))
        assertThat(songEntity.additionalTitles, equalTo(additionalTitles))
        assertThat(songEntity.themes, equalTo(themes))
        assertThat(songEntity.lyrics, equalTo(lyrics))
        assertThat(songEntity.lyricOrder, equalTo(lyricOrder))
        assertThat(songEntity.sourceId, equalTo(sourceId))
        assertThat(songEntity.sourceSystem, equalTo(sourceSystem))
    }

    @Test
    fun testToSong() {
        val song: Song = SongEntity.toSong(songEntity)
        assertThat(song.guid, equalTo(guid))
        assertThat(song.title, equalTo(title))
        assertThat(song.authors, equalTo(authors))
        assertThat(song.additionalTitles, equalTo(additionalTitles))
        assertThat(song.themes, equalTo(themes))
        assertThat(song.lyrics, equalTo(lyrics))
        assertThat(song.lyricOrder, equalTo(lyricOrder))
        assertThat(song.sourceId, equalTo(sourceId))
        assertThat(song.sourceSystem, equalTo(sourceSystem))
    }

    @Test
    fun testToSongMetadata() {
        val songMetadata: SongMetadata = SongEntity.toSongMetadata(songEntity)
        assertThat(songMetadata.guid, equalTo(guid))
        assertThat(songMetadata.title, equalTo(title))
        assertThat(songMetadata.authors, equalTo(authors))
    }

}
