package ch.alika.practice.employees

import ch.alika.practice.dtos.EmployeeDTO
import ch.alika.practice.dtos.EmployeeListDTO

interface EmployeeCrudActor {
    fun createsEmployee(employee: EmployeeDTO): Long
    fun seesEmployeeExists(employeeId: Long): Boolean
    fun getsEmployee(employeeId: Long): EmployeeDTO
    fun updatesEmployee(employeeId: Long, employee: EmployeeDTO)
    fun deletesEmployee(employeeId: Long)
    fun getsAllEmployees(): EmployeeListDTO
}


