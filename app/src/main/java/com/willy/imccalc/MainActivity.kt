package com.willy.imccalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.willy.imccalc.navigation.IMCNavHost
import com.willy.imccalc.ui.features.home.HomeScreen
import com.willy.imccalc.ui.theme.IMCCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .safeDrawingPadding()
            ) {
                IMCCalcTheme {
                    IMCNavHost()
                }
            }
        }
    }
}