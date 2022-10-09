package ch.alika.practice.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FizzBuzzControllerTests {

    @Autowired
    lateinit var fizzBuzzController: FizzBuzzController

    @Test
    fun integer_returns_string_value_of_integer() {
        assertThat(fizzBuzzController.getFizzBuzzResult(1)).isEqualTo("1")
    }

}

