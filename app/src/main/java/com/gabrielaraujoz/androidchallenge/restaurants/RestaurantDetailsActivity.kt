package com.gabrielaraujoz.androidchallenge.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielaraujoz.androidchallenge.R
import com.gabrielaraujoz.androidchallenge.restaurants.data.Database
import com.gabrielaraujoz.androidchallenge.restaurants.model.Restaurant

class RestaurantDetailsActivity : AppCompatActivity() {

    private lateinit var restaurante: Restaurant
    private lateinit var nome: String
    private lateinit var viewManager: GridLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RestaurantDetailsAdapter

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
        viewAdapter = RestaurantDetailsAdapter(pratos) {
            val intent = Intent(this, PratoDetailsActivity::class.java)
            intent.putExtra("NomePrato", it.nomePrato)
            intent.putExtra("DescricaoPrato", it.descricao)
            intent.putExtra("ImagemPrato", it.imagem)
            startActivity(intent)
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