package io.jrb.msvc.songs.resource

import io.jrb.common.test.Testable
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SongMetadataTest : Testable {

    lateinit var guid: String
    lateinit var title: String
    lateinit var authors: List<String>
    lateinit var songMetadata: SongMetadata

    @BeforeEach
    fun before() {
        guid = RandomStringUtils.randomAlphabetic(10, 25)
        title = RandomStringUtils.randomAlphabetic(10, 25)
        authors = randomStringList(RandomUtils.nextInt(3, 5))
        songMetadata = SongMetadata(
                guid = guid,
                title = title,
                authors = authors
        )
    }

    @Test
    fun testCreate() {
        MatcherAssert.assertThat(songMetadata.guid, CoreMatchers.equalTo(guid))
        MatcherAssert.assertThat(songMetadata.title, CoreMatchers.equalTo(title))
        MatcherAssert.assertThat(songMetadata.authors, CoreMatchers.equalTo(authors))
    }

}
