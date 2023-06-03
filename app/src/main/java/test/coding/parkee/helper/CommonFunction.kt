package test.coding.parkee.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object CommonFunction {

    @SuppressLint("SimpleDateFormat")
    fun getReleaseDate(releaseDate: String): String {
        val dateFormat = SimpleDateFormat("yyyy-mm-dd")
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
        val date = dateFormat.parse(releaseDate)
        return if (date != null) {
            simpleDateFormat.format(date)
        } else {
            releaseDate
        }
    }

}