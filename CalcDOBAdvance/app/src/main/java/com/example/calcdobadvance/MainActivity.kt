package com.example.calcdobadvance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    var txtByYears:TextView? = null
    var txtByMonths:TextView? = null
    var txtByDays:TextView? = null
    var txtByHours:TextView? = null
    var txtByMinutes:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDateClick:Button = findViewById(R.id.btnDatePicker)
        txtByYears = findViewById(R.id.txtByYear)
        txtByMonths = findViewById(R.id.txtByMonth)
        txtByDays = findViewById(R.id.txtByDay)
        txtByHours = findViewById(R.id.txtByHours)
        txtByMinutes = findViewById(R.id.txtByMinutes)

        btnDateClick.setOnClickListener{
            onClicker()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onClicker() {
        val myCanlendar = Calendar.getInstance()
        val year = myCanlendar.get(Calendar.YEAR)
        val month = myCanlendar.get(Calendar.MONTH)
        val day = myCanlendar.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_, sYear, sMonth, sDay ->
                val selectDate = "${sDay}/${sMonth+1}/${sYear}"
                txtByYears?.text = "${year - sYear} years"

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectDate)
                theDate?.let {
                    val selecDateInMinute = theDate.time / 60_000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinute = currentDate.time / 60_000

                        val disTimeInMinutes = currentDateInMinute - selecDateInMinute
                        val disTimeInHours = (disTimeInMinutes / 60).toInt()
                        val disTimeInDay = (disTimeInHours / 24).toInt()
                        val disTimeMonth = (disTimeInDay / 30).toInt()

                        txtByMonths?.text = "$disTimeMonth months"
                        txtByDays?.text = "$disTimeInDay days"
                        txtByHours?.text = "$disTimeInHours hours"
                        txtByMinutes?.text = "$disTimeInMinutes minutes"
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