package com.willy.imccalc.data

/**
 * Objeto responsável por concentrar todas as regras de negócio
 * relacionadas aos cálculos de saúde da aplicação.
 *
 * Histórico de correções e evolução:
 *
 * Problema original:
 * - O IMC estava retornando incorretamente o valor do peso.
 * - A altura estava sendo utilizada em centímetros diretamente,
 *   sem conversão para metros, invalidando a fórmula.
 *
 * Correções realizadas:
 * 1) Correção do cálculo do IMC:
 *    - A altura passou a ser convertida corretamente para metros.
 *    - O método agora recebe tipos numéricos (Long e Double),
 *      eliminando conversões indevidas dentro da regra de negócio.
 *
 * 2) Refatoração para MVVM:
 *    - Funções agora não retornam textos formatados para UI.
 *    - A lógica de cálculo ficou desacoplada da apresentação.
 *
 * 3) Funcionalidades adicionais exigidas pelo enunciado:
 *    - Taxa Metabólica Basal (TMB) – fórmula de Mifflin-St Jeor.
 *    - Peso ideal – fórmula de Devine.
 *    - Necessidade calórica diária baseada no nível de atividade.
 */

object Calculations {

    /**
     * Calcula o Índice de Massa Corporal (IMC).
     *
     * Fórmula:
     * IMC = peso (kg) / (altura (m)²)
     *
     * @param height altura em centímetros
     * @param weight peso em quilogramas
     */
    fun calculateIMC(height: Long, weight: Double): Double {
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }

    /**
     * Retorna a classificação do IMC de acordo com a OMS.
     *
     * OBS: Retorna apenas a classificação textual,
     * sem formatação de UI (responsabilidade da View).
     */
    fun imcClassification(imc: Double): String =
        when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso normal"
            imc < 30.0 -> "Sobrepeso"
            imc < 35.0 -> "Obesidade Grau I"
            imc < 40.0 -> "Obesidade Grau II"
            else -> "Obesidade Grau III"
        }

    /**
     * Calcula a Taxa Metabólica Basal (TMB)
     * utilizando a fórmula de Mifflin-St Jeor.
     *
     * Homens:
     * TMB = (10 × peso) + (6,25 × altura) − (5 × idade) + 5
     *
     * Mulheres:
     * TMB = (10 × peso) + (6,25 × altura) − (5 × idade) − 161
     */
    fun calculateTMB(
        weight: Double,
        height: Long,
        age: Int,
        sex: String
    ): Double {
        return if (sex == "M") {
            (10 * weight) + (6.25 * height) - (5 * age) + 5
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        }
    }

    /**
     * Calcula o peso ideal utilizando a fórmula de Devine.
     *
     * Homens:
     * Peso ideal = 50 + 2,3 × (altura em polegadas − 60)
     *
     * Mulheres:
     * Peso ideal = 45,5 + 2,3 × (altura em polegadas − 60)
     */
    fun calculateIdealWeight(height: Long, sex: String): Double {
        val heightInInches = height / 2.54
        return if (sex == "M") {
            50 + 2.3 * (heightInInches - 60)
        } else {
            45.5 + 2.3 * (heightInInches - 60)
        }
    }

    /**
     * Calcula a necessidade calórica diária.
     *
     * Fórmula:
     * Necessidade diária = TMB × fator de atividade
     *
     * Exemplos de fator de atividade:
     * - Sedentário: 1.2
     * - Leve: 1.375
     * - Moderado: 1.55
     * - Intenso: 1.725
     */
    fun calculateDailyCalories(
        tmb: Double,
        activityFactor: Double
    ): Double {
        return tmb * activityFactor
    }
}