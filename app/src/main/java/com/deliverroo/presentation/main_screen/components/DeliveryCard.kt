package com.deliverroo.presentation.main_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.deliverroo.domain.models.Delivery

@Composable
fun DeliveryCard(
    delivery: Delivery,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onSaveClick: () -> Unit,
    onCallCustomerClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = delivery.phoneNumber,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = delivery.name,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = delivery.deliveryComment,
                maxLines = 5,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tip average = X", //get from customer
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = delivery.tipReceived.toString(), //get from customer
                maxLines = 1,
            )
            Row(
            ) {
                IconButton(
                    onClick = onCallCustomerClick,
                    ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call customer"
                    )
                }
                IconButton(
                    onClick = onSaveClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Save delivery"
                    )
                }
            }


        }
    }
}