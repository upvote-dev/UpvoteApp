package dev.upvote

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings

expect val settings: Settings

@OptIn(ExperimentalSettingsApi::class)
expect val settingsSuspend: SuspendSettings

/* TODO:
expect val secureSettings
*/
