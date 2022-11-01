package ch.alika.practice.controllers

import ch.alika.practice.dtos.EmployeeDTO
import ch.alika.practice.dtos.EmployeeListDTO
import ch.alika.practice.dtos.EmployeeMapper
import ch.alika.practice.dtos.ObjectIdDTO
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
    fun getAllEmployees(): ResponseEntity<EmployeeListDTO> {
        val allEmployees = service.getAllEmployees()
            .stream()
            .map { this.convertToDTO(it) }
            .collect(Collectors.toList()) as ArrayList
        return ResponseEntity.ok().body(EmployeeListDTO(allEmployees))
    }

    @GetMapping("/{id}")
    fun getEmployee(@PathVariable id: Long): ResponseEntity<EmployeeDTO> {
        val employeeDTO = EmployeeMapper.mapEntityToDTO(service.getEmployeeById(id))
        return ResponseEntity.ok().body(employeeDTO)
    }

    @PostMapping("")
    fun newEmployee(@RequestBody employeeDTO: EmployeeDTO): ResponseEntity<Any> {
        val newEmployee = convertToEntity(employeeDTO)
        val objectIdDTO = ObjectIdDTO(service.newEmployee(newEmployee))
        return ResponseEntity.status(HttpStatus.CREATED).body(objectIdDTO)
    }

    @PutMapping("/{id}")
    fun replaceEmployee(@PathVariable id: Long, @RequestBody employeeDTO: EmployeeDTO): ResponseEntity<Any> {
        val newEmployee = convertToEntity(employeeDTO)
        service.replaceEmployee(id, newEmployee)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<Any> {
        service.deleteEmployeeById(id)
        return ResponseEntity.noContent().build()
    }

    private fun convertToEntity(employeeDTO: EmployeeDTO) = EmployeeMapper.mapDtoToEntity(employeeDTO)
    private fun convertToDTO(employee: Employee) = EmployeeMapper.mapEntityToDTO(employee)
}