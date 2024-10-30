package dev.upvote.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

import dev.upvote.api.HttpResponseException
import dev.upvote.api.ProfileApi
import dev.upvote.api.first_party.Profile
import dev.upvote.data.repository.profile.DefaultProfileContentRepository
import dev.upvote.data.repository.profile.ProfileContentRepository
import dev.upvote.httpClient

class ProfileContentViewModel(
    private val profileContentRepository: ProfileContentRepository = DefaultProfileContentRepository(
        ProfileApi(httpClient)
    )
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(ProfileContentState(userId = null, lastErrorStr = null, profile = null))
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

    fun getProfile() {
        viewModelScope.launch {
            try {
                val profile = profileContentRepository.getProfile().first()
                if (profile != null) {
                    setProfile(
                        profile = profile
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

    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            try {
                val updatedProfile = profileContentRepository.updateProfile(profile = profile).first()
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
