package com.example.stepogotchi_main.di

import android.content.Context
import com.example.stepogotchi_main.data.DatabaseRepositoryImpl
import com.example.stepogotchi_main.data.PreferencesRepositoryImpl
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.domain.repository.PreferencesRepository
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.AddExerciseUseCase
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.GetDataUseCase
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.InsertDataUseCase
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.UpdateDataUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetLeftStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetSystemStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.ResetSharedPreferencesUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.SaveLeftStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.SaveStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.SaveSystemStepsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm{
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Monster::class, Exercise::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }
    @Singleton
    @Provides
    fun provideMonsterRepository(realm: Realm): DatabaseRepository{
        return DatabaseRepositoryImpl(realm = realm)
    }
    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesRepository {
        return PreferencesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideGetStepsUseCase(preferencesHelper: PreferencesRepository): GetStepsUseCase {
        return GetStepsUseCase(preferencesHelper)
    }

    @Provides
    @Singleton
    fun provideSaveStepsUseCase(preferencesHelper: PreferencesRepository): SaveStepsUseCase {
        return SaveStepsUseCase(preferencesHelper)
    }
    @Provides
    @Singleton
    fun provideGetSystemStepsUseCase(preferencesHelper: PreferencesRepository): GetSystemStepsUseCase {
        return GetSystemStepsUseCase(preferencesHelper)
    }
    @Provides
    @Singleton
    fun provideGetStepsLeftUseCase(preferencesHelper: PreferencesRepository): GetLeftStepsUseCase {
        return GetLeftStepsUseCase(preferencesHelper)
    }
    @Provides
    @Singleton
    fun provideSaveLeftStepsUseCase(preferencesHelper: PreferencesRepository): SaveLeftStepsUseCase {
        return SaveLeftStepsUseCase(preferencesHelper)
    }

    @Provides
    @Singleton
    fun provideSaveSystemStepsUseCase(preferencesHelper: PreferencesRepository): SaveSystemStepsUseCase {
        return SaveSystemStepsUseCase(preferencesHelper)
    }
    @Provides
    @Singleton
    fun provideResetSharedPreferencesUseCase(preferencesHelper: PreferencesRepository): ResetSharedPreferencesUseCase {
        return ResetSharedPreferencesUseCase(preferencesHelper)
    }
    @Provides
    fun provideGetDataUseCase(repository: DatabaseRepository): GetDataUseCase {
        return GetDataUseCase(repository)
    }

    @Provides
    fun provideInsertDataUseCase(repository: DatabaseRepository): InsertDataUseCase {
        return InsertDataUseCase(repository)
    }

    @Provides
    fun provideUpdateDataUseCase(repository: DatabaseRepository): UpdateDataUseCase {
        return UpdateDataUseCase(repository)
    }
    @Provides
    fun provideAddExerciseUseCase(repository: DatabaseRepository): AddExerciseUseCase {
        return AddExerciseUseCase(repository)
    }

}