package ch.alika.practice.services

import ch.alika.practice.entities.Employee
import ch.alika.practice.exceptions.EntityNotFoundException
import ch.alika.practice.repositories.EmployeeDAO
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService(private val employeeDAO: EmployeeDAO) {

    @Transactional
    fun newEmployee(newEmployee: Employee): Long {
        employeeDAO.save(newEmployee)
        return newEmployee.id!!
    }

    @Transactional
    fun getEmployeeById(id: Long): Employee {
        val employee = employeeDAO.findById(id).orElseThrow {
            EntityNotFoundException("Employee id = $id not found")
        }
        return employee
    }

    @Transactional
    fun replaceEmployee(id: Long, newEmployee: Employee) {
        employeeDAO.findById(id).map {
            it.name = newEmployee.name
        }.orElseThrow {
            EntityNotFoundException("Employee id = $id not found")
        }
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
    fun getAllEmployees(): List<Employee> {
        return employeeDAO.findAll().map { i -> i }
    }
}