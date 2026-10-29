
package dev.marcosfarias.pokedex.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokeApiClientTest {

    private fun createClient(
        status: HttpStatusCode,
        body: String
    ): HttpClient {
        return HttpClient(
            MockEngine {
                respond(
                    content = body,
                    status = status,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        "application/json"
                    )
                )
            }
        )
    }

    @Test
    fun `pokemon endpoint returns successful response`() = runBlocking {
        val client = createClient(
            HttpStatusCode.OK,
            """{"name":"pikachu","id":25}"""
        )

        try {
            val response =
                client.get("https://test.local/pokemon/pikachu")

            assertEquals(HttpStatusCode.OK, response.status)
            assertTrue(response.bodyAsText().contains("pikachu"))
        } finally {
            client.close()
        }
    }

    @Test
    fun `pokemon endpoint handles not found`() = runBlocking {
        val client = createClient(
            HttpStatusCode.NotFound,
            """{"detail":"Not found"}"""
        )

        try {
            val response =
                client.get("https://test.local/pokemon/unknown")

            assertEquals(HttpStatusCode.NotFound, response.status)
        } finally {
            client.close()
        }
    }

    @Test
    fun `species endpoint returns successful response`() = runBlocking {
        val client = createClient(
            HttpStatusCode.OK,
            """{"name":"pikachu","is_legendary":false}"""
        )

        try {
            val response =
                client.get("https://test.local/pokemon-species/pikachu")

            assertEquals(HttpStatusCode.OK, response.status)
            assertTrue(response.bodyAsText().contains("pikachu"))
        } finally {
            client.close()
        }
    }

    @Test
    fun `species endpoint handles server error`() = runBlocking {
        val client = createClient(
            HttpStatusCode.InternalServerError,
            """{"error":"server error"}"""
        )

        try {
            val response =
                client.get("https://test.local/pokemon-species/pikachu")

            assertEquals(
                HttpStatusCode.InternalServerError,
                response.status
            )
        } finally {
            client.close()
        }
    }
}