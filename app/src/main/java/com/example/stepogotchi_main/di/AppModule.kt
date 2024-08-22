package com.example.stepogotchi_main.di

import android.app.Application
import android.content.Context
import android.hardware.SensorManager
import com.example.stepogotchi_main.domain.model.StepSensor
import com.example.stepogotchi_main.sensor.AndroidSensor
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStepCounter(app: Application): MeasurableSensor{
        return StepSensor(app)
    }
}