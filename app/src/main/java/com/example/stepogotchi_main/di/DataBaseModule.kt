package com.example.stepogotchi_main.di

import com.example.stepogotchi_main.data.MonsterRepositoryImpl
import com.example.stepogotchi_main.data.model.Item
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.MonsterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
                Monster::class, Item::class
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
}