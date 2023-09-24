package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.example.travelapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var kotaAsal: Array<String>
    private lateinit var kotaTujuan: Array<String>
    private lateinit var pesanButton: Button
    private lateinit var pilihTanggalDatePicker: DatePicker
    private lateinit var pilihWaktuTimePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil array dari string.xml
        kotaAsal = resources.getStringArray(R.array.Kota_asal)
        kotaTujuan = resources.getStringArray(R.array.Kota_tujuan)

        // inisiasi spiner
        initKotaAsalSpinner()
        initKotaTujuanSpinner()

        pesanButton = findViewById(R.id.pesan_btn)
        pilihTanggalDatePicker = findViewById(R.id.pilih_tanggal)
        pilihWaktuTimePicker = findViewById(R.id.pilih_waktu)

        // Set an OnClickListener untuk "Pesan Tiket" button
        pesanButton.setOnClickListener {
            // Get the selected date from the DatePicker
            val selectedDate = getSelectedDate()

            // Get the selected time from the TimePicker
            val selectedHour = pilihWaktuTimePicker.hour
            val selectedMinute = pilihWaktuTimePicker.minute

            // Combine the selected date and time with "pukul"
            val selectedDateTime = combineDateAndTime(selectedDate, selectedHour, selectedMinute)

            // Display a Toast notification with the combined date and time
            Toast.makeText(this, selectedDateTime, Toast.LENGTH_LONG).show()
        }
    }
//    fungsi spinner agar meenampilkan striing array berisi nama kota
    private fun initKotaAsalSpinner() {
        val adapterAsal = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kotaAsal)
        binding.kotaAsal.adapter = adapterAsal
    }

    private fun initKotaTujuanSpinner() {
        val adapterTujuan = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kotaTujuan)
        binding.kotaTujuan.adapter = adapterTujuan
    }

    private fun getSelectedDate(): String {
        val day = pilihTanggalDatePicker.dayOfMonth
        val month = pilihTanggalDatePicker.month + 1
        val year = pilihTanggalDatePicker.year

        return String.format(Locale.getDefault(), "%02d-%02d-%04d", day, month, year)
    }

    private fun combineDateAndTime(date: String, hour: Int, minute: Int): String {
        return "Tiket berhasil dipesan pada $date pukul ${String.format(Locale.getDefault(), "%02d:%02d", hour, minute)}"
    }
}






