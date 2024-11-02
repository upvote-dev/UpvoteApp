package dev.upvote

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import com.russhwolf.settings.coroutines.toSuspendSettings


@OptIn(ExperimentalSettingsApi::class)
actual val settingsSuspend: SuspendSettings = Settings().toSuspendSettings()

@OptIn(ExperimentalSettingsApi::class)
actual val settings: Settings = settingsSuspend.toBlockingSettings()
