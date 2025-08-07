package com.akzuza.indi.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akzuza.indi.data.Title

@Composable
fun TitleSearchItem(
    title: Title,
    openReader: () -> Unit,
    openDetails: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(onClick = openReader),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(fraction = 0.85f)
                .fillMaxHeight()
                .padding(8.dp),
            text = title.title,
            fontSize = 16.sp
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(30.dp)
                .offset(y = (-4).dp),
            onClick = openDetails,
        ) {
            Icon(
                contentDescription = null,
                imageVector = Icons.Default.MoreVert
            )
        }
    }
}