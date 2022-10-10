package com.are.magicboxtwo.ui.features.error

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .wrapContentWidth()
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 10.dp),
                text = stringResource(id = R.string.error_description),
                style = TextStyle(
                    color = MBTheme.colors.error,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            OutlinedButton(
                modifier = Modifier
                    .padding(start = 10.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, MBTheme.colors.error),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MBTheme.colors.errorContainer
                ),
                onClick = onClick
            ) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    style = TextStyle(
                        color = MBTheme.colors.onError,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview("ErrorContentPreview", locale = "ja")
@Composable
private fun ErrorContentPreview() {
    MagicBox2Theme {
        ErrorContent(
            onClick = {}
        )
    }
}