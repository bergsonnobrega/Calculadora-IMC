package com.bergsonnobrega.calculadoraimc

data class Recomendacao(
    val dieta: String,
    val exercicios: String,
    val imagemDieta: String,
    val imagemExercicios: String
)

object RecomendacaoManager {
    fun getRecomendacao(imc: Double): Recomendacao {
        return when {
            imc < 18.5 -> Recomendacao(
                dieta = "• Aumente o consumo de alimentos ricos em proteínas como carnes, ovos e leguminosas\n" +
                        "• Inclua carboidratos complexos como arroz integral, batata doce e pães integrais\n" +
                        "• Consuma gorduras saudáveis como abacate, castanhas e azeite de oliva\n" +
                        "• Faça refeições a cada 3 horas\n" +
                        "• Consuma suplementos vitamínicos se necessário",
                exercicios = "• Treino de força 3-4 vezes por semana\n" +
                           "• Exercícios compostos como agachamento, supino e levantamento terra\n" +
                           "• Descanso adequado entre os treinos\n" +
                           "• Evite exercícios aeróbicos em excesso",
                imagemDieta = "https://example.com/dieta_baixo_peso.jpg",
                imagemExercicios = "https://example.com/exercicios_baixo_peso.jpg"
            )
            imc in 18.5..24.9 -> Recomendacao(
                dieta = "• Mantenha uma dieta equilibrada com todos os grupos alimentares\n" +
                        "• Consuma frutas e verduras diariamente\n" +
                        "• Beba pelo menos 2 litros de água por dia\n" +
                        "• Evite alimentos processados e ricos em açúcar\n" +
                        "• Faça refeições regulares",
                exercicios = "• Pratique exercícios aeróbicos 3-4 vezes por semana\n" +
                           "• Inclua treino de força 2-3 vezes por semana\n" +
                           "• Mantenha-se ativo no dia a dia\n" +
                           "• Experimente diferentes modalidades esportivas",
                imagemDieta = "https://example.com/dieta_normal.jpg",
                imagemExercicios = "https://example.com/exercicios_normal.jpg"
            )
            imc in 25.0..29.9 -> Recomendacao(
                dieta = "• Reduza o consumo de carboidratos simples\n" +
                        "• Aumente o consumo de proteínas magras\n" +
                        "• Inclua mais fibras na dieta\n" +
                        "• Evite alimentos fritos e gordurosos\n" +
                        "• Controle as porções das refeições",
                exercicios = "• Pratique exercícios aeróbicos 4-5 vezes por semana\n" +
                           "• Inclua treino de força 2-3 vezes por semana\n" +
                           "• Aumente a intensidade gradualmente\n" +
                           "• Mantenha-se consistente nos treinos",
                imagemDieta = "https://example.com/dieta_sobrepeso.jpg",
                imagemExercicios = "https://example.com/exercicios_sobrepeso.jpg"
            )
            else -> Recomendacao(
                dieta = "• Consulte um nutricionista para um plano personalizado\n" +
                        "• Reduza calorias gradualmente\n" +
                        "• Priorize alimentos naturais e integrais\n" +
                        "• Evite alimentos processados e açúcares\n" +
                        "• Mantenha um diário alimentar",
                exercicios = "• Comece com exercícios de baixo impacto\n" +
                           "• Pratique atividades aeróbicas regularmente\n" +
                           "• Inclua treino de força para preservar massa muscular\n" +
                           "• Aumente a intensidade gradualmente\n" +
                           "• Consulte um profissional de educação física",
                imagemDieta = "https://example.com/dieta_obeso.jpg",
                imagemExercicios = "https://example.com/exercicios_obeso.jpg"
            )
        }
    }
} 