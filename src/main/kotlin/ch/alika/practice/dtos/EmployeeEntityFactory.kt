package ch.alika.practice.dtos

import ch.alika.practice.entities.EmployeeEntity

class EmployeeEntityFactory {
    fun createEmployeeEntity(employeeDTO: EmployeeDTO): EmployeeEntity {
        return EmployeeEntity(
            name = employeeDTO.name ?: ""
        )
    }

    fun createEmployeeDTO(employeeEntity: EmployeeEntity): EmployeeDTO {
        return EmployeeDTO(
            name = employeeEntity.name,
            id = employeeEntity.id
        )
    }

    fun applyEmployeeDtoToEmployeeEntity(employeeDTO: EmployeeDTO, employeeEntity: EmployeeEntity) {
        employeeEntity.name = employeeDTO.name ?: employeeEntity.name
    }
}