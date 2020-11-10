package com.gabrielaraujoz.androidchallenge.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielaraujoz.androidchallenge.R
import com.gabrielaraujoz.androidchallenge.home.data.Database
import com.gabrielaraujoz.androidchallenge.home.model.Restaurant

class RestaurantViewActivity : AppCompatActivity() {

    private lateinit var restaurante: Restaurant
    private lateinit var nome: String
    private lateinit var viewManager: GridLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_view)

        nome = intent.getStringExtra("Nome")!!

        encontrarRestaurante(nome)

        var imagemRestaurante = findViewById<ImageView>(R.id.imgRestaurantView)
        imagemRestaurante.setImageResource(restaurante.imagem)
        var nomeRestaurante = findViewById<TextView>(R.id.txtRestaurantView)
        nomeRestaurante.text = restaurante.nome

        val pratos = restaurante.pratos

        viewManager = GridLayoutManager(this, 2)
        recyclerView = findViewById<RecyclerView>(R.id.pratosLista)
        viewAdapter = RestaurantAdapter(pratos) {

        }

        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }






    }

    fun encontrarRestaurante(nome: String) {
        Database.restaurant.forEach() {
            if (nome == it.nome) {
                restaurante = it
            }
        }
    }
}