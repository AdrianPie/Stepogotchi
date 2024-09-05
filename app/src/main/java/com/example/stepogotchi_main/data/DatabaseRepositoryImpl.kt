package com.example.stepogotchi_main.data

import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    val realm: Realm
): DatabaseRepository {
    override fun getData(): Flow<Monster>{
        return realm.query<Monster>().asFlow().map { it.list.first() }
    }

    override suspend fun insertMonster(monster: Monster) {
        realm.write { copyToRealm(monster) }
    }

    override suspend fun updateMonster(monster: Monster) {
        realm.write {
            val existingMonster = this.query<Monster>("id == $0", monster.id).first().find()
            existingMonster?.apply {
                exp = monster.exp
                hungryLevel = monster.hungryLevel
                sleepLevel = monster.sleepLevel
                exercises = monster.exercises

            }
        }
    }
    override suspend fun addExercise(exercise: Exercise) {
        realm.write {
            val existingMonster = this.query<Monster>().first().find()
            existingMonster?.apply {
                exercises.add(exercise)
            }
        }
    }


}