package ch.alika.practice.testdata

import ch.alika.practice.entities.Employee
import ch.alika.practice.entities.Activity
import ch.alika.practice.repositories.EmployeeDAO
import ch.alika.practice.repositories.ActivityDAO
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseLoader(
    private val employeeDAO: EmployeeDAO,
    private val activityDAO: ActivityDAO,
) : CommandLineRunner  {

    override fun run(vararg args: String?) {

        // Frodo
        val bilbo = Employee(name = "Frodo Baggins")
        val workUnit = Activity(performedBy = bilbo, description = "carried the ring to Mount Doom")
        employeeDAO.save(bilbo)
        activityDAO.save(workUnit)
    }

}
