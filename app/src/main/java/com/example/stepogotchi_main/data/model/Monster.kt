package com.example.stepogotchi_main.data.model

import androidx.annotation.DrawableRes
import com.example.stepogotchi_main.R
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Monster: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var exp: Int = 0
    @Ignore
    var level: Int = when(exp) {
        in 0..100 -> 1
        in 101..500 -> 2
        in 501..100000 ->3
        else -> 3
    }
    var hungryLevel: Int = 0
    var sleepLevel: Int = 0
    var exercises: RealmList<Exercise> = realmListOf()
    @Ignore
    @DrawableRes
    val monsterPicture: Int = when(level){
        1-> R.drawable.monster_level_one
        2-> R.drawable.monster_level_two
        3-> R.drawable.monster_level_three
        else -> R.drawable.monster_level_three
    }
}
class Exercise: RealmObject {
    var date: String = ""
    var steps: Int = 0
}