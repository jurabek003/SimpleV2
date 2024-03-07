package uz.turgunboyevjurabek.simplev2.Model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.turgunboyevjurabek.simplev2.Model.madels.User

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insetUser(user: User)

    @Query("select *from UserTable")
    fun getAllUser():List<User>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun editUser(user: User)

}