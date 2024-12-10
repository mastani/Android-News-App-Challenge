package app.mastani.news.common

import nl.jacobras.humanreadable.HumanReadable
import java.text.SimpleDateFormat
import kotlinx.datetime.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getFormattedDate(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return sdf.format(calendar.time)
}

fun Date.toHumanReadableDate(): String {
    return HumanReadable.timeAgo(Instant.fromEpochMilliseconds(this.time))
}

fun String.toHumanReadableDate(): String {
    return HumanReadable.timeAgo(Instant.parse(this))
}

