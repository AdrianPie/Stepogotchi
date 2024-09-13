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
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var exp: Int = 0
    var hungryLevel: Int = 0
    var sleepLevel: Int = 0
    var exercises: RealmList<Exercise> = realmListOf()
}

class Exercise: RealmObject {
    var date: String = ""
    var steps: Int = 0
}