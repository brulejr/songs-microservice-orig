package io.jrb.msvc.songs.resource

import io.jrb.common.test.Testable
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.time.Instant
import java.time.Instant.now
import kotlin.random.Random.Default.nextInt

internal class ErrorResponseTest : Testable {

    lateinit var status: HttpStatus
    var code: Int = 0
    var message: String? = ""
    lateinit var timestamp: Instant
    lateinit var bindingErrors: List<String>
    lateinit var errorResponse: ErrorResponse

    @BeforeEach
    fun before() {
        status = HttpStatus.MULTI_STATUS
        code = nextInt(100, 200)
        message = randomAlphabetic(10, 25)
        timestamp = now()
        bindingErrors = randomStringList(RandomUtils.nextInt(3, 5))
        errorResponse = ErrorResponse(
                status = status,
                code = code,
                message = message,
                timestamp = timestamp,
                bindingErrors = bindingErrors
        )
    }

    @Test
    fun testCreate() {
        assertThat(errorResponse.status, equalTo(status))
        assertThat(errorResponse.code, equalTo(code))
        assertThat(errorResponse.message, equalTo(message))
        assertThat(errorResponse.timestamp, equalTo(timestamp))
        assertThat(errorResponse.bindingErrors, equalTo(bindingErrors))
    }

}
