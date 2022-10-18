package ch.alika.practice.employees

import ch.alika.practice.dtos.EmployeeDTO
import ch.alika.practice.exceptions.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class EmployeeCrudFeatureTests {

    @Autowired
    private lateinit var actor: EmployeeCrudActor

    @Nested
    @DisplayName("Given we have created one employee THEN we ...")
    inner class GivenOneNewEmployee {
        private val employeeId = actor.createsEmployee(createEmployeeDTO(name="Tony"))

        @Test
        @DisplayName("can find it")
        fun can_find_it() {
            assertThat(actor.seesEmployeeExists(employeeId)).isTrue
        }

        @DisplayName("can retrieve it")
        @Test
        fun can_retrieve_it() {
            assertThat(actor.getsEmployee(employeeId).id).isEqualTo(employeeId)
        }

        @Test
        @DisplayName("can find it amongst all employees")
        fun can_find_in_list_of_all_employees() {
            actor.createsEmployee(createEmployeeDTO())
            val allEmployees = actor.getsAllEmployees().employees

            // then
            assertThat(allEmployees.size).isGreaterThan(1)
            assertThat(allEmployees.filter { i -> i.id == employeeId }).isNotEmpty
        }

        @Test
        @DisplayName("can delete it")
        fun can_delete_it() {
            actor.deletesEmployee(employeeId)
            assertThat(actor.seesEmployeeExists(employeeId)).isFalse
        }

        @Test
        @DisplayName("can update it")
        fun can_update_it() {
            // given
            val employee = actor.getsEmployee(employeeId)
            // when
            actor.updatesEmployee(employeeId, employee.copy(name = "Something new"))
            // then
            val updatedEmployed = actor.getsEmployee(employeeId)
            assertThat(updatedEmployed.name).isEqualTo("Something new")
        }
    }

    @Nested
    @DisplayName("Given a non-existing employee THEN we ...")
    inner class GivenNonExistingEmployee {
        private val nonExistingEmployeeId = -1L

        @Test
        @DisplayName("cannot find it")
        fun cannot_find_it() {
            assertThat(actor.seesEmployeeExists(nonExistingEmployeeId)).isFalse
        }

        @Test
        @DisplayName("cannot retrieve it")
        fun cannot_retrieve_it() {
            assertThrows<EntityNotFoundException> {
                actor.getsEmployee(nonExistingEmployeeId)
            }
        }

        @Test
        @DisplayName("cannot update it")
        fun cannot_update_it() {
            assertThrows<EntityNotFoundException> {
                actor.updatesEmployee(nonExistingEmployeeId, createEmployeeDTO())
            }
        }

        @Test
        @DisplayName("cannot delete it")
        fun cannot_delete_it() {
            assertThrows<EntityNotFoundException> {
                actor.deletesEmployee(nonExistingEmployeeId)
            }
        }

        @Test
        @DisplayName("cannot find it amongst all employees")
        fun cannot_find_it_in_list_of_all_employees() {
            assertThat(actor.getsAllEmployees().employees.filter { i -> i.id == nonExistingEmployeeId }).isEmpty()
        }
    }
    
    private fun createEmployeeDTO(name: String = "") = EmployeeDTO(name = name)
}

