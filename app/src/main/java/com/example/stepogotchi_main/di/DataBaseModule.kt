package com.example.stepogotchi_main.di

import android.content.Context
import com.example.stepogotchi_main.data.MonsterRepositoryImpl
import com.example.stepogotchi_main.data.PreferencesRepositoryImpl
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.MonsterRepository
import com.example.stepogotchi_main.domain.repository.PreferencesRepository
import com.example.stepogotchi_main.domain.use_case.GetStepsUseCase
import com.example.stepogotchi_main.domain.use_case.SaveStepsUseCase
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
    fun provideMonsterRepository(realm: Realm): MonsterRepository{
        return MonsterRepositoryImpl(realm = realm)
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
}