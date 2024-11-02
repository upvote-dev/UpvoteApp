package dev.upvote

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings


actual val settings: Settings = StorageSettings()

@OptIn(ExperimentalSettingsApi::class)
actual val settingsSuspend: SuspendSettings = settings.toSuspendSettings()