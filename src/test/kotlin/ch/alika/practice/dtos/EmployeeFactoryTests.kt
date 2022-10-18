package ch.alika.practice.dtos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class EmployeeFactoryTests {

    private val factory = EmployeeEntityFactory()

    @Test
    fun empty_dto_creates_default_employee() {
        val entity = factory.createEmployeeEntity(EmployeeDTO())
        assertThat(entity.name).isEqualTo("")
    }

}

