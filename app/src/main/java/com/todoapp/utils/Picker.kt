package com.todoapp.utils

import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Picker(private var fragmentManager: FragmentManager, private var editText: EditText) {
    init {
        makeDatePicker()
    }

    private fun makeTimePicker() {
        val timePicker: MaterialTimePicker =
            MaterialTimePicker.Builder().setTitleText("Select Time")
                .setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.show(fragmentManager, "")
        timePicker.addOnPositiveButtonClickListener {
            hour = timePicker.hour
            minet = timePicker.minute
            val result = "$fulldate,$hour,$minet"
            editText.setText(result)
        }
    }

    private fun makeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("select date").build()
        datePicker.show(fragmentManager, "")
        datePicker.addOnPositiveButtonClickListener {
            val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            outputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            fulldate = outputDateFormat.format(it)
            makeTimePicker()
        }
    }
}