package ch.alika.practice.employees.impl

import ch.alika.practice.dtos.EmployeeDto
import ch.alika.practice.dtos.EmployeeListDto
import ch.alika.practice.dtos.ObjectIdDto
import ch.alika.practice.exceptions.EntityNotFoundException
import ch.alika.practice.employees.EmployeeCrudActor
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Mono

class WebClientBasedEmployeeCrudActor(private val webClient: WebTestClient) : EmployeeCrudActor {

    override fun createsEmployee(employee: EmployeeDto): Long {
        val result = webClient.post()
            .uri("/api/employees")
            .body(Mono.just(employee), EmployeeDto::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectBody<ObjectIdDto>()
            .returnResult().responseBody

        return result!!.id
    }

    override fun seesEmployeeExists(employeeId: Long): Boolean {
        return webClient.get()
            .uri("/api/employees/$employeeId")
            .exchange()
            .returnResult<Any>()
            .status
            .is2xxSuccessful
    }

    override fun getsEmployee(employeeId: Long): EmployeeDto {
        val result = webClient.get()
            .uri("/api/employees/$employeeId")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody<EmployeeDto>()
            .returnResult()

        if (result.status == HttpStatus.NOT_FOUND)
            throw EntityNotFoundException("Employee id = $employeeId")

        assertThat(result.status.is2xxSuccessful).isTrue
        return result.responseBody!!

    }

    override fun updatesEmployee(employeeId: Long, employee: EmployeeDto) {
        val result = webClient.put()
            .uri("/api/employees/$employeeId")
            .body(Mono.just(employee), EmployeeDto::class.java)
            .exchange()
            .expectBody()
            .returnResult()

        if (result.status == HttpStatus.NOT_FOUND)
            throw EntityNotFoundException("Employee id = $employeeId")

        assertThat(result.status.is2xxSuccessful).isTrue
    }

    override fun deletesEmployee(employeeId: Long) {
        val result = webClient.delete()
            .uri("/api/employees/$employeeId")
            .exchange()
            .returnResult<Any>()

        if (result.status == HttpStatus.NOT_FOUND)
            throw EntityNotFoundException("Employee id = $employeeId")

        assertThat(result.status).isEqualTo(HttpStatus.NO_CONTENT)
    }

    override fun getsAllEmployees(): EmployeeListDto {
        val result = webClient.get()
            .uri("/api/employees/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<EmployeeListDto>()
            .returnResult()

        return result.responseBody!!
    }

}