package ch.alika.practice.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class FizzBuzzControllerTests {

    @Test
    fun controller_invokes_fizzbuzz_function() {
        val result = FizzBuzzController().getFizzBuzzResult(6)
        assertThat(result).isEqualTo(FizzBuzzResult(n=6, result = "Fizz"))
    }

}

