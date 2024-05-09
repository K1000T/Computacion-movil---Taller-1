package com.example.tallercompumovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner



class MainActivity : AppCompatActivity() {

    private lateinit var combo_countries: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Busca y asigna la vista del spinner desde el diseño
        combo_countries = findViewById(R.id.spinnerContinentes)

        // Crea un adaptador utilizando el array de recursos "combo_countries"
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.combo_countries, android.R.layout.simple_spinner_item)

        // Asigna el adaptador al spinner
        combo_countries.adapter = adapter

        // Asigna el botón desde el diseño a una variable
        val mButton: Button = findViewById(R.id.buttonprincipal)

        // Configura un listener para el botón
        mButton.setOnClickListener {
            // Obtiene el continente seleccionado en el spinner
            val selectedContinent = combo_countries.selectedItem.toString()

            // Crea un intent para cambiar a la segunda actividad (MainActivity2)
            val intent = Intent(this, MainActivity2::class.java)

            // Agrega el continente seleccionado como dato extra al intent
            intent.putExtra("continente", selectedContinent)

            // Inicia la segunda actividad utilizando el intent
            startActivity(intent)
        }
    }



}