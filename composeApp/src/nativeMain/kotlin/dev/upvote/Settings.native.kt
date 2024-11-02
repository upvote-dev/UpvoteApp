package dev.upvote

import platform.Foundation.NSUserDefaults

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings


actual val settings: Settings = NSUserDefaultsSettings(NSUserDefaults())

@OptIn(ExperimentalSettingsApi::class)
actual val settingsSuspend: SuspendSettings = settings.toSuspendSettings()
