package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao.KomentarDAO
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity

@Database(entities = [KomentarEntity::class], version = 2, exportSchema = false)
abstract class KomentarDatabase: RoomDatabase() {

    abstract fun komentarDao(): KomentarDAO

    companion object{
        @Volatile
        private var INSTANCE: KomentarDatabase? = null

        fun getDatabase(context: Context): KomentarDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KomentarDatabase::class.java,
                    "komentar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}