package uz.turgunboyevjurabek.simplev2.Model.madels

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String?=null,

    @ColumnInfo(name = "lastName")
    var lastName: String?=null,

    @ColumnInfo(name = "number")
    var number: String?=null

//    constructor(id: Int?, name: String?, lastName: String?, number: String?) {
//        this.id = id
//        this.name = name
//        this.lastName = lastName
//        this.number = number
//    }
//
//    constructor(name: String?, lastName: String?, number: String?) {
//        this.name = name
//        this.lastName = lastName
//        this.number = number
//    }


)
