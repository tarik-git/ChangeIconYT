package com.example.animals

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.animals.model.IconItem
import com.example.animals.ui.theme.AnimalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AnimalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .windowInsetsPadding(WindowInsets.navigationBars),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = listOf(
                        IconItem(
                            iconRes = R.drawable.dog,
                            title = "Dog",
                            aliasName = "$packageName.MainActivityDog"
                        ),
                        IconItem(
                            iconRes = R.drawable.cat,
                            title = "Cat",
                            aliasName = "$packageName.MainActivityCat"
                        ),
                        IconItem(
                            iconRes = R.drawable.parrot,
                            title = "Parrot",
                            aliasName = "$packageName.MainActivityParrot"
                        )
                    )
                    LazyColumn (
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items( list ) { item -> 
                            Card (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 2.dp, bottom = 2.dp)
                                    .clickable {
                                               setIcon(item.aliasName)
                                    },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Row (
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ){
                                    Image(
                                        modifier = Modifier
                                            .width(55.dp)
                                            .height(55.dp),
                                        painter = painterResource(id = item.iconRes),
                                        contentDescription = item.title,
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp, end = 8.dp),
                                        text = item.title,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun setIcon(aliasName: String) {
        val aliases = listOf(
            "$packageName.MainActivityDog",
            "$packageName.MainActivityCat",
            "$packageName.MainActivityParrot"
        )

        // Disable all launcher aliases
        for (alias in aliases) {
            packageManager.setComponentEnabledSetting(
                ComponentName(baseContext, alias),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }

        // Enable the selected launcher alias
        packageManager.setComponentEnabledSetting(
            ComponentName(baseContext, aliasName),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

    }

}