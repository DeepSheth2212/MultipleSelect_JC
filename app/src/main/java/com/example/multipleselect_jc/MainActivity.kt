package com.example.multipleselect_jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.multipleselect_jc.ui.theme.MultipleSelect_JCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipleSelect_JCTheme {
                var items by remember{
                    mutableStateOf(
                        (1..20).map { 
                            ListItem(title = "title $it", isSelected = false)
                        }
                    )
                }

                //to get the selected items
                var selected_items = items.filter {
                    it.isSelected
                }

                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(items.size){i ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    items = items.mapIndexed { j, item ->
                                        if (i == j) {
                                            item.copy(isSelected = !item.isSelected)
                                        } else {
                                            item
                                        }
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = items[i].title)
                            if(items[i].isSelected){
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "selected",
                                    tint = Color.Green,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }

                //only selected items will be remaining after this button is clicked
                FloatingActionButton(onClick = {
                    items = selected_items
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            }
        }
    }
}

