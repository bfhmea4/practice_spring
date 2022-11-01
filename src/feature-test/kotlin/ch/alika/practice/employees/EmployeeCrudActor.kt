package ch.alika.practice.employees

import ch.alika.practice.dtos.EmployeeDto
import ch.alika.practice.dtos.EmployeeListDto

interface EmployeeCrudActor {
    fun createsEmployee(employee: EmployeeDto): Long
    fun seesEmployeeExists(employeeId: Long): Boolean
    fun getsEmployee(employeeId: Long): EmployeeDto
    fun updatesEmployee(employeeId: Long, employee: EmployeeDto)
    fun deletesEmployee(employeeId: Long)
    fun getsAllEmployees(): EmployeeListDto
}


