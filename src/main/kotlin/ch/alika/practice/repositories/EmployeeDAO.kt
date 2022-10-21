package ch.alika.practice.repositories

import ch.alika.practice.entities.Employee
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDAO : PagingAndSortingRepository<Employee, Long>