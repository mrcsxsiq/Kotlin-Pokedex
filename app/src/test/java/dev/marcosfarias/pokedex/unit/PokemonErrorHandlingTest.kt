package dev.marcosfarias.pokedex.unit

import org.junit.Assert.*
import org.junit.Test

class PokemonErrorHandlingTest {

    private fun messageFor(
        statusCode: Int,
        body: String
    ): String {
        return when {
            statusCode in 200..299 && body.isNotBlank() -> "success"
            statusCode in 200..299 -> "empty"
            statusCode == 404 -> "not_found"
            statusCode >= 500 -> "server_error"
            else -> "request_error"
        }
    }

    @Test
    fun `successful response returns success`() {
        assertEquals("success", messageFor(200, """{"name":"pikachu"}"""))
    }

    @Test
    fun `empty response returns empty`() {
        assertEquals("empty", messageFor(200, ""))
    }

    @Test
    fun `404 response returns not found`() {
        assertEquals("not_found", messageFor(404, ""))
    }

    @Test
    fun `server error returns server error`() {
        assertEquals("server_error", messageFor(500, ""))
    }

    @Test
    fun `other client errors return request error`() {
        assertEquals("request_error", messageFor(400, ""))
    }
}