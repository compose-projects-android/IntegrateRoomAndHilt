package org.compose_projects.IntegrateRoomAndHilt.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.compose_projects.IntegrateRoomAndHilt.data.local.database.Users
import org.compose_projects.IntegrateRoomAndHilt.data.local.database.UsersDao
import javax.inject.Inject


interface UsersRepository {
    val users: Flow<List<UsersImp>>
    suspend fun insert(user: UsersImp)

    suspend fun update(user: UsersImp)

    suspend fun delete(user: UsersImp)

}


class UsersRepositoryImp @Inject constructor(
    private val usersDao: UsersDao
): UsersRepository {
    override val users: Flow<List<UsersImp>>
        get() = usersDao.getUsers().map { items ->
            items.map {
                UsersImp(
                    id = it.id,
                    name = it.name,
                    description = it.description
                )
            }
        }

    override suspend fun insert(user: UsersImp) =
        usersDao.insertUser(
            Users(
                name = user.name,
                description = user.description
            )
        )

    override suspend fun update(user: UsersImp) =
        usersDao.updateUser(
            Users(
                name = user.name,
                description = user.description
            )
        )

    override suspend fun delete(user: UsersImp) =
        usersDao.deleteUser(
            Users(
                name = user.name,
                description = user.description
            )
        )

}