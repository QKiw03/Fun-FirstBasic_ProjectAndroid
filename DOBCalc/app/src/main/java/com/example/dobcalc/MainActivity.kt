package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var tvSelectedDate : TextView? = null
    var tvSelectedMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedMinutes = findViewById(R.id.tvSelectedMinites)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_, year, month, day ->
                val selectDate = "$day/${month+1}/$year"
                tvSelectedDate?.setText(selectDate) // Current date

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) // Language: English
                val theDate = sdf.parse(selectDate) // change String to Date
                theDate?.let { // It theDate not null -> executed code below
                    val selectedDateInMinutes = theDate.time / 60_000 // Time select: Calc from 1970

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) // Time current
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60_000

                        val diff =  currentDateInMinutes - selectedDateInMinutes

                        tvSelectedMinutes?.text = diff.toString()
                    }

                }

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
        dpd.show()
    }

}