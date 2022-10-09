package ch.alika.practice.controllers

import org.springframework.web.bind.annotation.*

@RestController
class FizzBuzzController {
    @GetMapping("/fizzbuzz/{n}")
    fun getFizzBuzzResult(@PathVariable("n") n: Int): String {
        return n.toString()
    }
}