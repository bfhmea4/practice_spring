package ch.alika.practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PracticeApplication

fun main(args: Array<String>) {
    runApplication<PracticeApplication>(*args)
}
