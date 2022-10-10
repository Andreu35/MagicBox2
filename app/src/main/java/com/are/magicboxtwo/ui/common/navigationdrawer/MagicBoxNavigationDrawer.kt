package com.are.magicboxtwo.ui.common.navigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.theme.Blue
import com.are.magicboxtwo.ui.theme.MBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicBoxNavigationDrawer(
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(270.dp)
                    .background(
                        color = MBTheme.colors.onPrimary
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                MagicBoxNavigationHeader()
                MagicBoxNavigationBody(
                    items = getNavigationItemsList(),
                    onItemClick = {}
                )
            }
        }
    ) {
        content()
    }
}

@Composable
fun MagicBoxNavigationHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(
                color = Blue
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(id = R.drawable.ic_box),
            contentDescription = null
        )
    }
}

@Composable
fun MagicBoxNavigationBody(
    items: List<MagicBoxNavigationItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp, color = MBTheme.colors.primary),
    onItemClick: (item: MagicBoxNavigationItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                    tint = MBTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.title,
                    style = itemTextStyle
                )
            }
        }
    }
}
