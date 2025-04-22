package com.bergsonnobrega.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultadoActivity : AppCompatActivity() {
    private lateinit var textPeso: TextView
    private lateinit var textAltura: TextView
    private lateinit var textResultado: TextView
    private lateinit var btnRecomendacoes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        textPeso = findViewById(R.id.text_peso)
        textAltura = findViewById(R.id.text_altura)
        textResultado = findViewById(R.id.text_resultado)
        btnRecomendacoes = findViewById(R.id.btn_recomendacoes)

        var bundle = intent.extras
        if (bundle != null) {
            val peso = bundle.getDouble("peso")
            val altura = bundle.getDouble("altura")

            textPeso.text = "Peso informado: $peso kg"
            textAltura.text = "Altura informada: $altura m"
            val imc = peso / (altura * altura)
            textResultado.text = if (imc < 18.5) {
                "Baixo Peso"
            } else if (imc in 18.5..24.9) {
                "Peso Normal"
            } else if (imc in 25.0..29.9) {
                "Sobrepeso"
            } else {
                "Obesidade"
            }

            btnRecomendacoes.setOnClickListener {
                val intent = Intent(this, RecomendacaoActivity::class.java)
                intent.putExtra("peso", peso)
                intent.putExtra("altura", altura)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}