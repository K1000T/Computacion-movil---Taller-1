package com.example.tallercompumovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tallercompumovil.databinding.ActivityMain2Binding
import com.example.tallercompumovil.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    private var listacontinentes = ArrayList<Continente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el objeto de binding
        binding = ActivityMain2Binding.inflate(layoutInflater)

        // Obtener la raíz de la vista inflada y establecerla como contenido de la actividad
        setContentView(binding.root)

        // Aquí puedes llamar a tus funciones u operaciones
        inicializarContinentes()
    }

    // Función para cargar el archivo JSON desde los recursos
    private fun loadJSONFromAsset(fileName: String): JSONObject {
        val json: String
        try {
            val inputStream: InputStream = assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return JSONObject()
        }
        return JSONObject(json)
    }

    private fun inicializarContinentes() {
        // Cargar el archivo JSON desde los recursos
        val json = loadJSONFromAsset("paises.json")

        // Obtener el arreglo JSON llamado "Countries"
        val paisesJSON = json.getJSONArray("Countries")
        // Recorrer el arreglo JSON para inicializar la lista de continentes
        for (i in 0 until paisesJSON.length()) {
            val countryJson = paisesJSON.getJSONObject(i)

                Log.d("ContinenteDebug", "Encontrado continente: ${countryJson.getString("Name")}")
                val area = countryJson.optInt("Area", 0)
                val numericCode = countryJson.optInt("NumericCode", 0)

                // Crear un objeto Continente con los datos del JSON
                val continente = Continente(
                    countryJson.getString("Name"),
                    countryJson.getString("Alpha2Code"),
                    countryJson.getString("Alpha3Code"),
                    countryJson.getString("NativeName"),
                    countryJson.getString("Region"),
                    countryJson.getString("SubRegion"),
                    countryJson.getString("Latitude"),
                    countryJson.getString("Longitude"),
                    area,
                    numericCode,
                    countryJson.getString("NativeLanguage"),
                    countryJson.getString("CurrencyCode"),
                    countryJson.getString("CurrencyName"),
                    countryJson.getString("CurrencySymbol"),
                    countryJson.getString("Flag"),
                    countryJson.getString("FlagPng")
                )
                listacontinentes.add(continente)

        }

        // Inicializar el adaptador con la lista de continentes y el contexto
        val adapter = ItemAdapter(this, listacontinentes)
        // Establecer el adaptador en la vista de lista
        binding.listacontinentes.adapter = adapter
    }
}
