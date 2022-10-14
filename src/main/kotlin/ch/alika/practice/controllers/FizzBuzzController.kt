package ch.alika.practice.controllers

import ch.alika.practice.domain.fizzbuzz
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

data class FizzBuzzResult(val n: Int, val result: String)


@RestController
class FizzBuzzController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/fizzbuzz/{n}")
    fun getFizzBuzzResult(@PathVariable("n") n: Int): FizzBuzzResult {
        logger.debug("compute FizzBuzz Value = $n")
        return FizzBuzzResult(n = n, result = fizzbuzz(n))
    }

}