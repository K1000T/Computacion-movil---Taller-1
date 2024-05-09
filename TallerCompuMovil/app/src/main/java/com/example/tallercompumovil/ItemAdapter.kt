package com.example.tallercompumovil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.tallercompumovil.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bumptech.glide.Glide


class ItemAdapter (private val context: Context, private val continetes: List<Continente>) : BaseAdapter() {
    override fun getCount(): Int {
        return continetes.size
    }

    override fun getItem(position: Int): Any {
        return continetes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val country = continetes[position]

        // Inflar la vista de elemento de país desde el layout
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_country, parent, false)

        // Obtener referencias a las vistas dentro del layout
        val countryName = view.findViewById<TextView>(R.id.countryName)
        val nativeName = view.findViewById<TextView>(R.id.nativeName)
        val countryCode = view.findViewById<TextView>(R.id.countryCode)
        val currencyName = view.findViewById<TextView>(R.id.currencyName)
        val currencySymbol = view.findViewById<TextView>(R.id.currencySymbol)
        val countryPhoto = view.findViewById<ImageView>(R.id.countryPhoto)
        val floatingActionButton =
            view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        // Establecer los valores de las vistas con los datos del país actual
        countryName.text = country.Name
        nativeName.text = country.NativeName
        countryCode.text = country.Alpha3Code
        currencyName.text = country.CurrencyName
        currencySymbol.text = country.CurrencySymbol
        Log.d("ImageURL", country.FlagPng)

        // Cargar la foto del país en el ImageView utilizando una función auxiliar
        loadCountryPhoto(country.FlagPng, countryPhoto)

        // Asignar un listener al botón de acción flotante (FloatingActionButton)
        floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${country.NumericCode}")
            context.startActivity(intent)
        }

        // Asignar un listener a la vista del país (CardView) para abrir una nueva actividad
        view.findViewById<CardView>(R.id.cardCountries).setOnClickListener {
            // Crear el intent para abrir la nueva actividad
            val intent = Intent(context, ActividadContinente::class.java)
            // Agregar información del país al intent en el formato correspondiente
            intent.putExtra("name", country.Name)
            intent.putExtra("code", "${country.Alpha2Code} - ${country.Alpha3Code}")
            intent.putExtra("region", country.Region)
            intent.putExtra(
                "subregion",
                "${country.SubRegion} (${country.Latitude}, ${country.Longitude})"
            )
            intent.putExtra("currency", "(${country.CurrencyCode}) - ${country.CurrencySymbol}")
            intent.putExtra("area", "Area: ${country.Area}")
            intent.putExtra("numeric", "NumericCode: ${country.NumericCode}")
            intent.putExtra("flag", country.FlagPng)
            // Iniciar la actividad
            context.startActivity(intent)
        }

        return view
    }

    // Función para cargar las imágenes en un hilo secundario
    private fun loadCountryPhoto(url: String, imageView: ImageView) {
        // Usar corutinas para cargar la imagen de manera asíncrona
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                // Cargar la imagen usando Glide en un hilo de fondo
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit() // Cargar la imagen en segundo plano
                    .get() // Obtener el mapa de bits
            }
            // Establecer la imagen en el ImageView
            imageView.setImageBitmap(bitmap)
        }
    }

}