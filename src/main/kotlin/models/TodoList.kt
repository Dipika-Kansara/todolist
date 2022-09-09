package models

import ObjectIdAsStringSerializer
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId


@Serializable
data class TodoList(
    val name: String,
    @Serializable(with = ObjectIdAsStringSerializer::class) val _id: Id<TodoList> = newId(),
)