package com.willy.imccalc.data

object Calculations {
    /** Gemini - início
     * Prompt: O que tem de errado no meu app? Abaixo é meu calculations.kt. O problema é que
     * quando eu clico em calcular, aparece o valor do peso na mensagem, ao invés do valor do
     * cálculo (está chamando as funções desse arquivo). Exemplo: Em altura, eu coloco 145 e
     * peso eu coloco 50. E a mensagem que aparece é: IMC: 50.00 Obesidade Mórbida (Grau III).
     * Para contexto: as informações estão sendo cadastradas normalmente no banco de dados,
     * mas o result está sendo o mesmo valor que o weight.
     *
     * package com.willy.imccalc.data
     *
     * object Calculations {
     *     fun calculateIMC(height: String, weight: String): Double {
     *          val weightFormatted = weight.replace(",", ".").toDouble()
     *          val heightFormatted = height.toLong()
     *
     *          val imc = weightFormatted / (heightFormatted / 100 * heightFormatted / 100)
     *
     *          return imc
     *     }
     *
     *     fun showIMCLevel(imc: Double): String {
     *         val imcFormatted = String.format("%.2f", imc)
     *
     *         when {
     *             imc < 18.5 -> return "IMC: $imcFormatted \n Abaixo do peso"
     *             imc in 18.5..24.9 -> return "IMC: $imcFormatted \n Peso normal"
     *             imc in 25.0..29.9 -> return "IMC: $imcFormatted \n Sobrepeso"
     *             imc in 30.0..34.9 -> return "IMC: $imcFormatted \n Obesidade (Grau I)"
     *             imc in 35.0..39.9 -> return "IMC: $imcFormatted \n Obesidade Severa (Grau II)"
     *             else -> return "IMC: $imcFormatted \n Obesidade Mórbida (Grau III)"
     *         }
     *     }
     * }
     */

    fun calculateIMC(height: String, weight: String): Double {
        val weightFormatted = weight.replace(",", ".").toDouble()
        val heightFormatted = height.toLong()

        val heightInMeters = heightFormatted / 100.0

        val imc = weightFormatted / (heightInMeters * heightInMeters)

        return imc
    }

    /** Gemini - final */

    fun showIMCLevel(imc: Double): String {
        val imcFormatted = String.format("%.2f", imc)

        when {
            imc < 18.5 -> return "IMC: $imcFormatted \n Abaixo do peso"
            imc in 18.5..24.9 -> return "IMC: $imcFormatted \n Peso normal"
            imc in 25.0..29.9 -> return "IMC: $imcFormatted \n Sobrepeso"
            imc in 30.0..34.9 -> return "IMC: $imcFormatted \n Obesidade (Grau I)"
            imc in 35.0..39.9 -> return "IMC: $imcFormatted \n Obesidade Severa (Grau II)"
            else -> return "IMC: $imcFormatted \n Obesidade Mórbida (Grau III)"
        }
    }
}