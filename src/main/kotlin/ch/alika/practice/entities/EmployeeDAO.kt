package ch.alika.practice.entities

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDAO : CrudRepository<EmployeeEntity, Long> {
}