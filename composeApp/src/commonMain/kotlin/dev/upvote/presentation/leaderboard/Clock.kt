package dev.upvote.presentation.leaderboard

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

@OptIn(DelicateCoroutinesApi::class)
internal object MyClock {
    val time = flow {
        while (true) {
            emit(epochSeconds())
            delay(1.seconds)
        }
    }.stateIn(GlobalScope, SharingStarted.Eagerly, 0L)
}

fun epochSeconds(): Long {
    return Instant.fromEpochSeconds(0).epochSeconds
}

fun formatTime(epochSeconds: Long): String {
    return epochSeconds.toString()
}
