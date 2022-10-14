package ch.alika.practice.features

import ch.alika.practice.controllers.FizzBuzzController
import ch.alika.practice.controllers.FizzBuzzResult
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

interface FizzBuzzActor {
    fun getsFizzBuzzResult(n: Int): String
}

class WebClientBasedFizzBuzzActor(private val client: WebTestClient) : FizzBuzzActor {

    companion object {
        fun localHostServer(): WebClientBasedFizzBuzzActor {
            return WebClientBasedFizzBuzzActor(WebTestClient.bindToServer().baseUrl("http://localhost:8080").build())
        }
        fun mockServer(): WebClientBasedFizzBuzzActor {
            return WebClientBasedFizzBuzzActor(WebTestClient.bindToController(FizzBuzzController()).build())
        }
    }

    override fun getsFizzBuzzResult(n: Int): String {
        val responseBody: FizzBuzzResult? = client.get()
            .uri("/api/fizzbuzz/$n")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody<FizzBuzzResult>()
            .returnResult().responseBody

        return responseBody?.result ?: ""
    }
}