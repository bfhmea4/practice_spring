package ch.alika.practice.controllers

import ch.alika.practice.dtos.EmployeeDto
import ch.alika.practice.dtos.EmployeeListDto
import ch.alika.practice.dtos.EmployeeMapper
import ch.alika.practice.dtos.ObjectIdDto
import ch.alika.practice.entities.Employee
import ch.alika.practice.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@RestController
@RequestMapping("/api/employees")
class EmployeeController @Autowired constructor(private val service: EmployeeService) {

    @GetMapping("/")
    fun getAllEmployees(): ResponseEntity<EmployeeListDto> {
        val allEmployees = service.getAllEmployees()
            .stream()
            .map { this.convertToDto(it) }
            .collect(Collectors.toList()) as ArrayList
        return ResponseEntity.ok().body(EmployeeListDto(allEmployees))
    }

    @GetMapping("/{id}")
    fun getEmployee(@PathVariable id: Long): ResponseEntity<EmployeeDto> {
        val employeeDTO = convertToDto(service.getEmployeeById(id))
        return ResponseEntity.ok().body(employeeDTO)
    }

    @PostMapping("")
    fun newEmployee(@RequestBody employeeDTO: EmployeeDto): ResponseEntity<Any> {
        val newEmployee = convertToEntity(employeeDTO)
        val objectIdDTO = ObjectIdDto(service.newEmployee(newEmployee))
        return ResponseEntity.status(HttpStatus.CREATED).body(objectIdDTO)
    }

    @PutMapping("/{id}")
    fun replaceEmployee(@PathVariable id: Long, @RequestBody employeeDTO: EmployeeDto): ResponseEntity<Any> {
        val newEmployee = convertToEntity(employeeDTO)
        service.replaceEmployee(id, newEmployee)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<Any> {
        service.deleteEmployeeById(id)
        return ResponseEntity.noContent().build()
    }

    private fun convertToEntity(employeeDto: EmployeeDto) = EmployeeMapper.mapDtoToEntity(employeeDto)
    private fun convertToDto(employee: Employee) = EmployeeMapper.mapEntityToDto(employee)
}