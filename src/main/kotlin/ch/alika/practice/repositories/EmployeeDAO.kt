package ch.alika.practice.repositories

import ch.alika.practice.entities.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDAO : CrudRepository<Employee, Long>