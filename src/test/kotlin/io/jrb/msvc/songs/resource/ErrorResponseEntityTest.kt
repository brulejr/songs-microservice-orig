package io.jrb.msvc.songs.resource

import io.jrb.common.test.Testable
import io.mockk.every
import io.mockk.mockk
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.util.MultiValueMap

internal class ErrorResponseEntityTest : Testable {

    private val errorResponse: ErrorResponse = mockk<ErrorResponse>()

    @BeforeEach
    fun before() {
        every { errorResponse.status } returns HttpStatus.I_AM_A_TEAPOT
    }

    @Test
    fun testCreate() {
        val errorResponseEntity = ErrorResponseEntity(errorResponse)
        assertThat(errorResponseEntity, notNullValue())
        assertThat(errorResponseEntity.statusCode, equalTo(HttpStatus.I_AM_A_TEAPOT))
    }

    @Test
    fun testBadRequest() {
        val message = randomAlphabetic(10, 25)
        val errorResponseEntity = ErrorResponseEntity.badRequest(message)
        assertThat(errorResponseEntity, notNullValue())
        assertThat(errorResponseEntity.statusCode, equalTo(HttpStatus.BAD_REQUEST))
        assertThat(errorResponseEntity.body?.message, equalTo(message))
    }

    @Test
    fun testBadRequest_BindingErrors() {
        val message = randomAlphabetic(10, 25)
        val bindingErrors = randomStringList(RandomUtils.nextInt(3, 5))
        val errorResponseEntity = ErrorResponseEntity.badRequest(message, bindingErrors)
        assertThat(errorResponseEntity, notNullValue())
        assertThat(errorResponseEntity.statusCode, equalTo(HttpStatus.BAD_REQUEST))
        assertThat(errorResponseEntity.body?.message, equalTo(message))
        assertThat(errorResponseEntity.body?.bindingErrors, equalTo(bindingErrors))
    }

    @Test
    fun testNotFound() {
        val message = randomAlphabetic(10, 25)
        val errorResponseEntity = ErrorResponseEntity.notFound(message)
        assertThat(errorResponseEntity, notNullValue())
        assertThat(errorResponseEntity.statusCode, equalTo(HttpStatus.NOT_FOUND))
        assertThat(errorResponseEntity.body?.message, equalTo(message))
    }

    @Test
    fun testServerError() {
        val message = randomAlphabetic(10, 25)
        val errorResponseEntity = ErrorResponseEntity.serverError(message)
        assertThat(errorResponseEntity, notNullValue())
        assertThat(errorResponseEntity.statusCode, equalTo(HttpStatus.INTERNAL_SERVER_ERROR))
        assertThat(errorResponseEntity.body?.message, equalTo(message))
    }

}
