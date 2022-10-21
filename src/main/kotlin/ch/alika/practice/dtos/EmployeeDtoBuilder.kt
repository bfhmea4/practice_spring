package ch.alika.practice.dtos

import ch.alika.practice.entities.Employee

class EmployeeDtoBuilder private constructor() {

    companion object {
        fun createEmployeeDtoFromEntity(employeeEntity: Employee): EmployeeDTO {
            return EmployeeDTO(
                name = employeeEntity.name,
                id = employeeEntity.id
            )
        }
    }
}