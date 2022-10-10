package ch.alika.practice.controllers

import ch.alika.practice.domain.fizzbuzz
import org.springframework.web.bind.annotation.*

@RestController
class FizzBuzzController {
    @GetMapping("/api/fizzbuzz/{n}")
    fun getFizzBuzzResult(@PathVariable("n") n: Int): String {
        return fizzbuzz(n)
    }

}