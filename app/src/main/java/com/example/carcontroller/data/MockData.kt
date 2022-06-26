package com.example.carcontroller.data

import com.example.carcontroller.R
import com.example.carcontroller.domain.entity.*

object MockData {
    val vehicles = listOf(
        Vehicle(
            model = "BMW i8",
            calculatedMileage = Distance(length = 100),
            imageResId = R.drawable.ic_bmw,
            doors = Doors(DoorsLockStatus.UNLOCKED),
            engine = Engine(EngineStatus.INACTIVE)
        ),
        Vehicle(
            model = "Mercedes Benz",
            calculatedMileage = Distance(length = 120),
            imageResId = R.drawable.ic_mercedes,
            doors = Doors(DoorsLockStatus.UNLOCKED),
            engine = Engine(EngineStatus.INACTIVE)
        ),
        Vehicle(
            model = "Audi",
            calculatedMileage = Distance(length = 130),
            imageResId = R.drawable.ic_audi,
            doors = Doors(DoorsLockStatus.UNLOCKED),
            engine = Engine(EngineStatus.INACTIVE)
        ),
    )
}