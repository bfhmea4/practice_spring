package ch.alika.practice.dtos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class EmployeeEntityBuilderTests {

    @Test
    fun empty_dto_creates_default_employee() {
        val entity = EmployeeMapper.mapDtoToEntity(EmployeeDTO())
        assertThat(entity.name).isEqualTo("")
    }

}

