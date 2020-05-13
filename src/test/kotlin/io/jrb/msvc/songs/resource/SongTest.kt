package io.jrb.msvc.songs.resource

import io.jrb.common.test.Testable
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SongTest : Testable {

    lateinit var guid: String
    lateinit var title: String
    lateinit var authors: List<String>
    lateinit var additionalTitles: List<String>
    lateinit var themes: List<String>
    lateinit var lyrics: Map<String, List<String>>
    lateinit var lyricOrder: List<String>
    lateinit var sourceId: String
    lateinit var sourceSystem: String
    lateinit var song: Song

    @BeforeEach
    fun before() {
        guid = RandomStringUtils.randomAlphabetic(10, 25)
        title = RandomStringUtils.randomAlphabetic(10, 25)
        authors = randomStringList(RandomUtils.nextInt(3, 5))
        additionalTitles = randomStringList(RandomUtils.nextInt(3, 5))
        themes = randomStringList(RandomUtils.nextInt(3, 5))
        lyrics = buildMap(
                RandomUtils.nextInt(3, 5),
                { i -> RandomStringUtils.randomAlphabetic(10, 25) },
                { i -> randomStringList(RandomUtils.nextInt(3, 5)) }
        )
        lyricOrder = randomStringList(RandomUtils.nextInt(3, 5))
        sourceId = RandomStringUtils.randomAlphabetic(10, 25)
        sourceSystem = RandomStringUtils.randomAlphabetic(10, 25)
        song = Song(
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
        MatcherAssert.assertThat(song.guid, CoreMatchers.equalTo(guid))
        MatcherAssert.assertThat(song.title, CoreMatchers.equalTo(title))
        MatcherAssert.assertThat(song.authors, CoreMatchers.equalTo(authors))
        MatcherAssert.assertThat(song.additionalTitles, CoreMatchers.equalTo(additionalTitles))
        MatcherAssert.assertThat(song.themes, CoreMatchers.equalTo(themes))
        MatcherAssert.assertThat(song.lyrics, CoreMatchers.equalTo(lyrics))
        MatcherAssert.assertThat(song.lyricOrder, CoreMatchers.equalTo(lyricOrder))
        MatcherAssert.assertThat(song.sourceId, CoreMatchers.equalTo(sourceId))
        MatcherAssert.assertThat(song.sourceSystem, CoreMatchers.equalTo(sourceSystem))
    }

}
