package ch.alika.practice.controllers

import ch.alika.practice.domain.fizzbuzz
import ch.alika.practice.dtos.FizzBuzzResultDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class FizzBuzzController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/fizzbuzz/{n}")
    fun getFizzBuzzResult(@PathVariable("n") n: Int): ResponseEntity<FizzBuzzResultDto> {
        logger.debug("compute FizzBuzz Value = $n")
        return ResponseEntity.ok().body(
            FizzBuzzResultDto(n = n, result = fizzbuzz(n))
        )
    }

}