package ch.alika.practice.dtos

import ch.alika.practice.entities.EmployeeEntity

class EmployeeDtoBuilder private constructor() {

    companion object {
        fun createEmployeeDtoFromEntity(employeeEntity: EmployeeEntity): EmployeeDTO {
            return EmployeeDTO(
                name = employeeEntity.name,
                id = employeeEntity.id
            )
        }
    }
}