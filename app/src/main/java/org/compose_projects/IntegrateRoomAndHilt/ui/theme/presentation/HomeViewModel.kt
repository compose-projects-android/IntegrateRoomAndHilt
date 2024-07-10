package org.compose_projects.IntegrateRoomAndHilt.ui.theme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.compose_projects.IntegrateRoomAndHilt.data.UsersImp
import org.compose_projects.IntegrateRoomAndHilt.data.UsersRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    val usersUiState: StateFlow<UsersUiState> = usersRepository
        .users.map<List<UsersImp>, UsersUiState> {UsersUiState.Success(data = it)}
        .catch { emit(UsersUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UsersUiState.Loading)


    fun insertUser(user: UsersImp) =
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.insert(user)
        }

    fun updateUser(user: UsersImp) =
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.update(user)
        }

    fun deleteUser(user: UsersImp) =
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.delete(user)
        }



}

sealed interface UsersUiState {
    data object Loading : UsersUiState
    data class Error(val throwable: Throwable) : UsersUiState
    data class Success(val data: List<UsersImp>) : UsersUiState
}