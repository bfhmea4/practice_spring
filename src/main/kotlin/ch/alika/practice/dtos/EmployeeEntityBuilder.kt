package ch.alika.practice.dtos

import ch.alika.practice.entities.EmployeeEntity

class EmployeeEntityBuilder private constructor() {

    companion object {
        fun createEmployeeEntityFromDTO(employeeDTO: EmployeeDTO): EmployeeEntity {
            return EmployeeEntity(
                name = employeeDTO.name ?: ""
            )
        }

        fun applyEmployeeDtoToEntity(employeeDTO: EmployeeDTO, employeeEntity: EmployeeEntity) {
            employeeEntity.name = employeeDTO.name ?: employeeEntity.name
        }
    }

}