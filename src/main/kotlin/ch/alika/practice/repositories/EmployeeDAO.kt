package ch.alika.practice.repositories

import ch.alika.practice.entities.EmployeeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDAO : CrudRepository<EmployeeEntity, Long>