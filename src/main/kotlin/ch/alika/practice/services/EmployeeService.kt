package ch.alika.practice.services

import ch.alika.practice.dtos.EmployeeDTO
import ch.alika.practice.dtos.EmployeeEntityFactory
import ch.alika.practice.dtos.EmployeeListDTO
import ch.alika.practice.dtos.ObjectIdDTO
import ch.alika.practice.entities.EmployeeDAO
import ch.alika.practice.exceptions.EntityNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService(private val employeeDAO: EmployeeDAO) {
    private val factory = EmployeeEntityFactory()

    @Transactional
    fun newEmployee(employeeDTO: EmployeeDTO): ObjectIdDTO {
        val newEmployee = factory.createEmployeeEntity(employeeDTO)
        employeeDAO.save(newEmployee)
        return ObjectIdDTO(newEmployee.id!!)
    }

    @Transactional
    fun getEmployeeById(id: Long): EmployeeDTO {
        val employee = employeeDAO.findById(id).orElseThrow {
            EntityNotFoundException("Employee id = $id not found")
        }
        return factory.createEmployeeDTO(employee)
    }

    @Transactional
    fun updateEmployeeById(id: Long, employeeDTO: EmployeeDTO) {
        val currentEmployee = employeeDAO.findById(id).orElseThrow() {
            EntityNotFoundException("Employee id = $id not found")
        }
        factory.applyEmployeeDtoToEmployeeEntity(employeeDTO, currentEmployee)
    }

    @Transactional
    fun deleteEmployeeById(id: Long) {
        try {
            employeeDAO.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Employee id = $id not found")
        }
    }

    @Transactional
    fun getAllEmployees(): EmployeeListDTO {
        val employees =  ArrayList<EmployeeDTO>()
        employeeDAO.findAll().forEach { i -> employees.add(factory.createEmployeeDTO(i))}
        return EmployeeListDTO(employees = employees)
    }

}