
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

class PokeApiServiceTest {

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
    fun `evolution chain endpoint returns data`() = runBlocking {
        val client = createClient(
            HttpStatusCode.OK,
            """{"id":1,"chain":{"species":{"name":"bulbasaur"}}}"""
        )

        try {
            val response =
                client.get("https://test.local/evolution-chain/1")

            assertEquals(HttpStatusCode.OK, response.status)
            assertTrue(response.bodyAsText().contains("bulbasaur"))
        } finally {
            client.close()
        }
    }

    @Test
    fun `evolution chain endpoint handles not found`() = runBlocking {
        val client = createClient(
            HttpStatusCode.NotFound,
            """{"detail":"Not found"}"""
        )

        try {
            val response =
                client.get("https://test.local/evolution-chain/999999")

            assertEquals(HttpStatusCode.NotFound, response.status)
        } finally {
            client.close()
        }
    }
}