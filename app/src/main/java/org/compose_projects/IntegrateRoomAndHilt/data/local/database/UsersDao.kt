package org.compose_projects.IntegrateRoomAndHilt.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
    val description: String? = null
)

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getUsers(): Flow<List<Users>>

    @Insert
    fun insertUser(user: Users)

    @Update
    fun updateUser(user: Users)

    @Delete
    fun deleteUser(user: Users)

}