package com.codandotv.streamplayerapp.feature_detail.presentation.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("LongParameterList")
@Composable
fun DetailStreamButtonAction(
    buttonsColors: ButtonColors,
    imageVector: ImageVector,
    imageVectorColor: Color,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(4.dp),
        modifier = modifier,
        colors = buttonsColors,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector,
                contentDescription = null,
                tint = imageVectorColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
    }
}