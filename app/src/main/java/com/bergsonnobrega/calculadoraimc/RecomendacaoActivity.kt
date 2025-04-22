package com.bergsonnobrega.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.progressindicator.LinearProgressIndicator

class RecomendacaoActivity : AppCompatActivity() {
    private lateinit var textDieta: TextView
    private lateinit var textExercicios: TextView
    private lateinit var progressProteina: LinearProgressIndicator
    private lateinit var progressGordura: LinearProgressIndicator
    private lateinit var progressCarbs: LinearProgressIndicator
    private lateinit var textCalorias: TextView
    private lateinit var textCaloriasLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recomendacao)

        initializeViews()
        setupMacronutrientes()

        val bundle = intent.extras
        if (bundle != null) {
            val peso = bundle.getDouble("peso")
            val altura = bundle.getDouble("altura")
            val imc = peso / (altura * altura)

            val recomendacao = RecomendacaoManager.getRecomendacao(imc)
            textDieta.text = recomendacao.dieta
            textExercicios.text = recomendacao.exercicios

            // Calcular calorias baseado no peso
            val caloriasBase = calcularCaloriasBase(peso, altura, imc)
            setupProgressos(caloriasBase)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeViews() {
        textDieta = findViewById(R.id.text_dieta)
        textExercicios = findViewById(R.id.text_exercicios)
        progressProteina = findViewById(R.id.progress_proteina)
        progressGordura = findViewById(R.id.progress_gordura)
        progressCarbs = findViewById(R.id.progress_carbs)
        textCalorias = findViewById(R.id.text_calorias)
        textCaloriasLabel = findViewById(R.id.text_calorias_label)
    }

    private fun setupMacronutrientes() {
        progressProteina.apply {
            setIndicatorColor(getColor(R.color.primary))
            trackColor = getColor(R.color.primary_light)
            progress = 65
        }
        
        progressGordura.apply {
            setIndicatorColor(getColor(R.color.primary))
            trackColor = getColor(R.color.primary_light)
            progress = 45
        }
        
        progressCarbs.apply {
            setIndicatorColor(getColor(R.color.primary))
            trackColor = getColor(R.color.primary_light)
            progress = 85
        }
    }

    private fun calcularCaloriasBase(peso: Double, altura: Double, imc: Double): Int {
        // Fórmula de Harris-Benedict para calcular TMB (Taxa Metabólica Basal)
        val tmb = when {
            imc < 18.5 -> peso * 35 // Precisa ganhar peso
            imc in 18.5..24.9 -> peso * 30 // Manutenção
            imc in 25.0..29.9 -> peso * 25 // Redução moderada
            else -> peso * 20 // Redução mais acentuada
        }
        return tmb.toInt()
    }

    private fun setupProgressos(caloriasBase: Int) {
        textCalorias.text = caloriasBase.toString()

        // Configurar macronutrientes baseado nas necessidades
        val proteinaPorKg = when {
            caloriasBase > 2500 -> 2.2 // Alta necessidade calórica
            caloriasBase in 2000..2500 -> 2.0 // Necessidade moderada
            else -> 1.8 // Baixa necessidade calórica
        }

        progressProteina.progress = 65 // Exemplo: 65% da meta de proteína atingida
        progressGordura.progress = 45 // Exemplo: 45% da meta de gordura atingida
        progressCarbs.progress = 85 // Exemplo: 85% da meta de carboidratos atingida
    }
} 