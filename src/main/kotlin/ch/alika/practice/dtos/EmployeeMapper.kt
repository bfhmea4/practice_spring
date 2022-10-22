package ch.alika.practice.dtos

import ch.alika.practice.entities.Employee

class EmployeeMapper private constructor() {

    companion object {
        fun mapDtoToEntity(employeeDTO: EmployeeDTO): Employee {
            return Employee(
                name = employeeDTO.name ?: ""
            )
        }

        fun mapEntityToDTO(employeeEntity: Employee): EmployeeDTO {
            return EmployeeDTO(
                name = employeeEntity.name,
                id = employeeEntity.id
            )
        }
    }

}