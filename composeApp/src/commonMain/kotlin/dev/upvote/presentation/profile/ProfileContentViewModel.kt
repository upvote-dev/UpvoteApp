package dev.upvote.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.upvote.api.AuthError

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import dev.upvote.api.HttpResponseException
import dev.upvote.api.ProfileApi
import dev.upvote.api.first_party.DefaultProfile
import dev.upvote.api.first_party.Profile
import dev.upvote.api.first_party.ProfileOptional
import dev.upvote.data.repository.profile.DefaultProfileContentRepository
import dev.upvote.data.repository.profile.ProfileContentRepository
import dev.upvote.globalGlobalState
import dev.upvote.httpClient
import kotlinx.coroutines.flow.firstOrNull

class ProfileContentViewModel(
    private val profileContentRepository: ProfileContentRepository = DefaultProfileContentRepository(
        ProfileApi(httpClient)
    )
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ProfileContentState(
                userId = null,
                lastErrorStr = null,
                profile = DefaultProfile
            )
        )
    val uiState: StateFlow<ProfileContentState> = _uiState.asStateFlow()

    private fun setErrorStr(errorStr: String) {
        _uiState.update {
            it.copy(
                lastErrorStr = errorStr
            )
        }
    }

    private fun setProfile(profile: Profile) {
        _uiState.update {
            it.copy(
                profile = profile
            )
        }
    }

    fun toggleShowEdit() {
        _uiState.update {
            it.copy(
                showEdit = !_uiState.value.showEdit
            )
        }
    }

    fun toggleShowSettings() {
        _uiState.update {
            it.copy(
                showSettings = !_uiState.value.showSettings
            )
        }
    }

    fun resetToDefault() {
        _uiState.update {
            it.copy(
                profile = DefaultProfile
            )
        }
    }

    fun getProfile() {
        if (globalGlobalState.value.token == null) {
            setErrorStr("[authError] Not logged in")
            return
        }
        viewModelScope.launch {
            try {
                val profile = profileContentRepository.getProfile().firstOrNull()
                profile?.let {
                    setProfile(profile = it)
                }
            } catch (e: CancellationException) {
                currentCoroutineContext().ensureActive()
            } catch (e: HttpResponseException) {
                if (e.response.status == HttpStatusCode.NotFound) {
                    setErrorStr("Profile not found")
                } else {
                    setErrorStr("[${e.response.status}] ${e.response.bodyAsText()}")
                }
            } catch (e: SocketTimeoutException) {
                setErrorStr("[SocketTimeoutException] ${e.message}")
            }
        }
    }

    fun updateProfile(profile: ProfileOptional) {
        if (globalGlobalState.value.token == null) {
            throw AuthError("not logged in")
        }
        viewModelScope.launch {
            try {
                val updatedProfile =
                    profileContentRepository.updateProfile(profile = profile).first()
                if (updatedProfile != null) {
                    setProfile(
                        profile = updatedProfile
                    )
                }
            } catch (e: CancellationException) {
                currentCoroutineContext().ensureActive()
            } catch (e: HttpResponseException) {
                if (e.response.status == HttpStatusCode.NotFound) {
                    setErrorStr("Product not found")
                } else {
                    setErrorStr("[${e.response.status}] ${e.response.bodyAsText()}")
                }
            } catch (e: SocketTimeoutException) {
                setErrorStr("[SocketTimeoutException] ${e.message}")
            }
        }
    }
}
