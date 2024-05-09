package com.example.tallercompumovil

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class ActividadContinente: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        // Rellenar los TextViews con los datos del intent
        val countryName = findViewById<TextView>(R.id.countryName)
        val countryCode = findViewById<TextView>(R.id.countryCode)
        val region = findViewById<TextView>(R.id.Region)
        val subRegion = findViewById<TextView>(R.id.subRegion)
        val currency = findViewById<TextView>(R.id.currency)
        val area = findViewById<TextView>(R.id.area)
        val numericCode = findViewById<TextView>(R.id.numeriCode)
        val countryPhoto = findViewById<ImageView>(R.id.countryPhoto)

        // Obtener datos del intent y establecerlos en los TextViews correspondientes
        countryName.text = intent.getStringExtra("name")
        countryCode.text = intent.getStringExtra("code")
        region.text = intent.getStringExtra("region")
        subRegion.text = intent.getStringExtra("subregion")
        currency.text = intent.getStringExtra("currency")
        area.text = intent.getStringExtra("area")
        numericCode.text = intent.getStringExtra("numeric")

        // Cargar la foto del país usando Glide
        val flagUrl = intent.getStringExtra("flag")
        Glide.with(this)
            .load(flagUrl)
            .into(countryPhoto)
    }

}