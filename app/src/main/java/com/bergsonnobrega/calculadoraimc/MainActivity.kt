package com.bergsonnobrega.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular: Button
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCalcular = findViewById(R.id.btn_calcular)
        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)

        btnCalcular.setOnClickListener {
            val peso = editPeso.text.toString().toDoubleOrNull()
            val altura = editAltura.text.toString().toDoubleOrNull()

            if (peso != null && altura != null) {
                val intent = Intent(this, resultadoActivity::class.java)
                intent.putExtra("peso", peso)
                intent.putExtra("altura", altura)
                startActivity(intent)
            } else {
                editPeso.error = "Digite um peso válido"
                editAltura.error = "Digite uma altura válida"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}