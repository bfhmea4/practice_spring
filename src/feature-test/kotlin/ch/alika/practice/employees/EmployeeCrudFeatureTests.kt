package ch.alika.practice.employees

import ch.alika.practice.dtos.EmployeeDTO
import ch.alika.practice.dtos.EmployeeListDTO
import ch.alika.practice.exceptions.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun new_employee_can_be_found() {
        // when
        val employeeId = actor.createsEmployee(createEmployeeDTO())
        // then
        assertThat(actor.seesEmployeeExists(employeeId)).isTrue
    }

    @Test
    fun non_existing_employee_cannot_be_found() {
        assertThat(actor.seesEmployeeExists(-1)).isFalse
    }

    @Test
    fun get_employee_returns_employee() {
        // given
        val employeeId = actor.createsEmployee(createEmployeeDTO())
        // when
        val employee = actor.getsEmployee(employeeId)
        // then
        assertThat(employee.id).isEqualTo(employeeId)
    }

    @Test
    fun get_non_existing_employee_throws_not_found() {
        assertThrows<EntityNotFoundException> {
            actor.getsEmployee(-1)
        }
    }

    @Test
    fun update_employee_changes_employee_properties() {
        // given
        val employeeId = actor.createsEmployee(createEmployeeDTO())
        val employee = actor.getsEmployee(employeeId)
        // when
        actor.updatesEmployee(employeeId, employee.copy(name = "Something new"))
        // then
        val updatedEmployed = actor.getsEmployee(employeeId)
        assertThat(updatedEmployed.name).isEqualTo("Something new")
    }

    @Test
    fun update_non_existing_employee_throws_not_found() {
        assertThrows<EntityNotFoundException> {
            actor.updatesEmployee(-1, createEmployeeDTO())
        }
    }

    @Test
    fun deleted_employee_is_gone() {
        // given
        val employeeId = actor.createsEmployee(createEmployeeDTO())
        // when
        actor.deletesEmployee(employeeId)
        // then
        assertThat(actor.seesEmployeeExists(employeeId)).isFalse
    }

    @Test
    fun delete_non_existing_employee_throws_not_found() {
         assertThrows<EntityNotFoundException> {
             actor.deletesEmployee(-1)
         }
    }

    @Test
    fun get_all_employees_returns_non_empty_list() {
        // given
        actor.createsEmployee(createEmployeeDTO(name="A"))
        actor.createsEmployee(createEmployeeDTO(name="B"))
        // when
        val employeeList: EmployeeListDTO = actor.getsAllEmployees()
        // then
        assertThat(employeeList.employees).isNotEmpty
    }
    
    private fun createEmployeeDTO(name: String = "") = EmployeeDTO(name = name)
}

