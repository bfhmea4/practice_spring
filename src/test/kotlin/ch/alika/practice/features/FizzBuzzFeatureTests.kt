package ch.alika.practice.features

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig


class TestConfiguration {

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

@SpringJUnitConfig(TestConfiguration::class)
class FizzBuzzFeatureTests {

    @Autowired
    private lateinit var actor: FizzBuzzActor

    @Test
    fun integer_not_divisible_by_three_or_five_returns_string_value_of_integer() {
        val result = actor.getsFizzBuzzResult(1)
        assertThat(result).isEqualTo("1")
    }

    @Test
    fun integer_divisible_by_three_returns_fizz() {
        val result = actor.getsFizzBuzzResult(9)
        assertThat(result).isEqualTo("Fizz")
    }

    @Test
    fun integer_divisible_by_five_returns_fizzbuzz() {
        val result = actor.getsFizzBuzzResult(25)
        assertThat(result).isEqualTo("Buzz")
    }

    @Test
    fun integer_divisible_by_three_and_five_returns_fizzbuzz() {
        val result = actor.getsFizzBuzzResult(30)
        assertThat(result).isEqualTo("FizzBuzz")
    }
}

