package com.example.carcontroller.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcontroller.R
import com.example.carcontroller.base.managers.SnackbarManager
import com.example.carcontroller.domain.entity.Vehicle
import com.example.carcontroller.domain.enums.SnackbarType
import com.example.carcontroller.domain.enums.ViewState
import com.example.carcontroller.ui.components.ActionButton
import com.example.carcontroller.ui.components.AlertDialog
import com.example.carcontroller.ui.components.HeaderText
import com.example.carcontroller.ui.theme.*
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    viewModel.alertDialogState?.let {
        AlertDialog(
            title = stringResource(it.titleRes),
            message = it.message,
            onPositiveButtonClick = {
                when (it.action) {
                    HomeViewModel.DialogAction.LOCK -> {
                        viewModel.onLockDoors()
                        viewModel.onDialogDismissed()
                    }
                    HomeViewModel.DialogAction.UNLOCK -> {
                        viewModel.onUnlockDoors()
                        viewModel.onDialogDismissed()
                    }
                }
            },
            onDismiss = {
                viewModel.onDialogDismissed()
            }
        )
    }

    val coroutineScope = rememberCoroutineScope()

    viewModel.snackbarMessage?.let {
        SnackbarManager.showSnackbar(
            message = stringResource(it),
            coroutineScope = coroutineScope,
            type = SnackbarType.SUCCESS
        )
        viewModel.snackbarMessage = null
    }

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val pagerState = rememberPagerState()

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                viewModel.onCurrentVehicleChange(page)
            }
        }

        TopBar(vehicle = viewModel.currentVehicle)

        VehiclesPager(
            pagerState = pagerState,
            vehicles = viewModel.vehicles,
            modifier = Modifier.aspectRatio(2f)
        )
        VehiclesPagerIndicator(
            pagerState = pagerState,
            indicatorsCount = viewModel.vehicles.size
        )
        VehicleControlsGridLayout(modifier = Modifier.weight(1f), viewModel = viewModel)
    }
}

@Composable
private fun VehicleControlsGridLayout(modifier: Modifier, viewModel: HomeViewModel) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(PADDING_MEDIUM),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            VehicleControlsGridItem(
                title = stringResource(R.string.doors),
                subtitle = viewModel.currentVehicle.doors.status.displayText,
                firstButtonClick = {
                    viewModel.askForLockDoors()
                },
                secondButtonClick = {
                    viewModel.askForUnlockDoors()
                },
                firstButtonIconId = R.drawable.act_lock,
                secondButtonIconId = R.drawable.act_unlock,
                firstButtonState = viewModel.currentVehicle.doors.lockedState,
                secondButtonState = viewModel.currentVehicle.doors.unlockedState,
            )
        }
        item {
            VehicleControlsGridItem(
                title = stringResource(R.string.engine),
                firstButtonText = stringResource(R.string.start),
                secondButtonText = stringResource(R.string.stop),
                firstButtonState = viewModel.currentVehicle.engine.state,
                secondButtonState = viewModel.currentVehicle.engine.state,
            )
        }
    }
}

@Composable
private fun VehicleControlsGridItem(
    title: String,
    subtitle: String? = null,
    firstButtonState: ViewState,
    secondButtonState: ViewState,
    firstButtonIconId: Int? = null,
    firstButtonText: String? = null,
    secondButtonText: String? = null,
    secondButtonIconId: Int? = null,
    firstButtonClick: () -> Unit = {},
    secondButtonClick: () -> Unit = {},
) {
    GridItem(
        title = title,
        subtitle = subtitle,
    ) {
        ActionButton(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            state = firstButtonState,
            iconId = firstButtonIconId,
            text = firstButtonText,
            onClick = firstButtonClick
        )
        ActionButton(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            state = secondButtonState,
            iconId = secondButtonIconId,
            text = secondButtonText,
            onClick = secondButtonClick
        )
    }
}

@Composable
private fun GridItem(
    title: String,
    subtitle: String? = null,
    content: @Composable RowScope.() -> Unit
) {
    Column(horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.padding(vertical = PADDING_EXTRA_SMALL),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderText(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = title
            )
            if (subtitle != null) {
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(1.dp)
                        .height(18.dp),
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = subtitle, style = MaterialTheme.typography.subtitle2
                )
            }
        }
        Row(
            modifier = Modifier
                .shadow(
                    elevation = ELEVATION_SMALL,
                    shape = Shapes.small
                )
                .background(MaterialTheme.colors.surface),
            content = content
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VehiclesPagerIndicator(
    pagerState: PagerState,
    indicatorsCount: Int,
    modifier: Modifier = Modifier
) {
    HorizontalPagerIndicator(
        modifier = modifier.padding(vertical = PADDING_MEDIUM),
        pagerState = pagerState,
        pageCount = indicatorsCount,
        activeColor = MaterialTheme.colors.primaryVariant,
        inactiveColor = MaterialTheme.colors.secondary,
        indicatorHeight = 2.dp,
        indicatorShape = RectangleShape,
        indicatorWidth = 20.dp,
        spacing = PADDING_EXTRA_SMALL
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun VehiclesPager(
    pagerState: PagerState,
    vehicles: List<Vehicle>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        modifier = modifier,
        count = vehicles.size,
        state = pagerState
    ) { page ->
        VehicleImage(vehicles[page])
    }
}

@Composable
private fun VehicleImage(
    vehicle: Vehicle
) {
    Image(
        modifier = Modifier.fillMaxHeight(),
        contentScale = ContentScale.FillHeight,
        painter = painterResource(vehicle.imageResId),
        contentDescription = vehicle.model
    )
}

@Composable
private fun TopBar(vehicle: Vehicle, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Text(
            modifier = modifier.weight(1f),
            text = vehicle.model,
            textAlign = TextAlign.End
        )
        Divider(
            modifier = Modifier
                .padding(vertical = PADDING_MEDIUM, horizontal = PADDING_SMALL)
                .width(1.dp)
                .height(24.dp),
            color = MaterialTheme.colors.primaryVariant
        )
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(end = PADDING_EXTRA_SMALL),
                painter = painterResource(R.drawable.notif_gas),
                tint = MaterialTheme.colors.primary,
                contentDescription = ""
            )
            Text(
                modifier = modifier.weight(1f),
                text = vehicle.calculatedMileage.toString(),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start
            )
        }
    }
}