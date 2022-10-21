package ch.alika.practice.entities

import javax.persistence.*

@Entity
class Activity (
    @ManyToOne
    val performedBy: Employee,
    val description: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)