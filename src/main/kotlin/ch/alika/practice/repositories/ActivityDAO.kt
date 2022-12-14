package ch.alika.practice.repositories

import ch.alika.practice.entities.Activity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource(collectionResourceRel = "activities", path = "activities")
@Repository
interface ActivityDAO : CrudRepository<Activity, Long>