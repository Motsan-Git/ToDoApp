package com.todoapp.utils

import android.util.Log
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

    private fun makeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("select date").build()
        datePicker.show(fragmentManager, "select date")
        datePicker.addOnPositiveButtonClickListener {
            val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            outputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            fulldate = outputDateFormat.format(it)
            makeTimePicker()
        }
    }
    private fun makeTimePicker() {
        val timePicker: MaterialTimePicker =
            MaterialTimePicker.Builder().setTitleText("Select time")
                .setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.show(fragmentManager, "Select time")
        timePicker.addOnPositiveButtonClickListener {
            hour =if (timePicker.hour < 10) "0${timePicker.hour}" else "${timePicker.hour}"
            minute = if (timePicker.minute < 10) "0${timePicker.minute}" else "${timePicker.minute}"
            val result = "$fulldate,$hour:$minute"
            editText.setText(result)

        }
    }

}