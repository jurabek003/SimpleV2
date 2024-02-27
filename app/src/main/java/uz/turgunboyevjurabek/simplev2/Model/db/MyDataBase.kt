package uz.turgunboyevjurabek.simplev2.Model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import kotlin.concurrent.Volatile
const val start=3
@Database(entities = [(User::class)], version = start, exportSchema = true)
abstract class MyDataBase:RoomDatabase() {

    abstract fun roomInstens():MyDao


    companion object {

        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: MyDataBase? = null

        fun getInstance(context: Context): MyDataBase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDataBase::class.java,
                        "employee_database"
                    )
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}