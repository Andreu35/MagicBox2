package com.are.magicboxtwo.ui.common.topappbar

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.are.magicboxtwo.ui.theme.MagicBox2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    onSearch: (query: String) -> Unit,
    onClear: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    BackHandler(
        enabled = true,
        onBack = onClear
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = query,
        placeholder = {
            Text(text = "Search your favourite movie...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clickable {
                        if (query.isNotEmpty()) {
                            query = ""
                        } else {
                            onClear()
                        }
                    },
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            query = it
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        )
    )
}

@Composable
@Preview(name = "SearchAppBarPreview")
@Preview(name = "SearchAppBarPreview", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SearchAppBarPreview() {
    MagicBox2Theme {
        SearchAppBar(
            onSearch = {},
            onClear = {}
        )
    }
}