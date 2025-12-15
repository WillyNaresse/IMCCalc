package com.willy.imccalc.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.willy.imccalc.domain.IMC
import com.willy.imccalc.domain.imc1
import com.willy.imccalc.ui.theme.IMCCalcTheme
import java.time.format.DateTimeFormatter

@Composable
fun IMCCalcItem(
    imc: IMC,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                val formattedIMC = String.format("%.2f", imc.result)
                Text(text = "IMC: ${formattedIMC}", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(8.dp))

                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val dataFormatada = imc.date.format(formatter)

                Text(text = dataFormatada, style = MaterialTheme.typography.bodyLarge)

            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
private fun IMCCalcItemPreview() {
    IMCCalcTheme {
        IMCCalcItem(
            imc = imc1,
            onItemClick = TODO(),
            onDeleteClick = TODO(),
            modifier = TODO()
        )
    }
}