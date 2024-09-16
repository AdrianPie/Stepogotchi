package com.example.stepogotchi_main.di

import android.app.Application
import com.example.stepogotchi_main.sensor.StepSensor
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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