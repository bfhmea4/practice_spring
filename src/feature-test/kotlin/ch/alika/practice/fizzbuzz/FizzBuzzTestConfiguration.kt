package ch.alika.practice.fizzbuzz

import ch.alika.practice.fizzbuzz.impl.WebClientBasedFizzBuzzActor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FizzBuzzTestConfiguration {

    @Value("\${practice.test.localhost:false}")
    private var localhost: Boolean = false

    @Bean
    fun fizzBuzzActor(): FizzBuzzActor {
        return if (localhost) {
            WebClientBasedFizzBuzzActor.localHostServer()
        } else {
            WebClientBasedFizzBuzzActor.mockServer()
        }
    }
}