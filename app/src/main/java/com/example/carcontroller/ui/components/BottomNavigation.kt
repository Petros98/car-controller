package com.example.carcontroller.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.carcontroller.domain.enums.MenuSections
import com.example.carcontroller.ui.theme.PADDING_EXTRA_SMALL

@Composable
fun BottomNavigation(navController: NavController) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        MenuSections.values().forEach { item ->
            val isSelected = currentRoute == item.route
            val color = if (isSelected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary
            BottomNavigationItem(
                modifier = Modifier.padding(0.dp),
                icon = {
                    Column(modifier = Modifier.size(32.dp), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Top) {
                        if (isSelected) {
                            Divider(
                                modifier = Modifier.fillMaxWidth(),
                                thickness = 2.dp,
                                color = color
                            )
                        }
                        Icon(
                            modifier = Modifier.padding(top = PADDING_EXTRA_SMALL),
                            imageVector = item.icon,
                            contentDescription = stringResource(item.title)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.subtitle1,
                        color = color
                    )
                },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.primary,
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}