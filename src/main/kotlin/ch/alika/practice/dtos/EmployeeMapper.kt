package ch.alika.practice.dtos

import ch.alika.practice.entities.Employee

class EmployeeMapper private constructor() {

    companion object {
        fun mapDtoToEntity(employeeDto: EmployeeDto): Employee {
            return Employee(
                name = employeeDto.name ?: ""
            )
        }

        fun mapEntityToDto(employeeEntity: Employee): EmployeeDto {
            return EmployeeDto(
                name = employeeEntity.name,
                id = employeeEntity.id
            )
        }
    }

}