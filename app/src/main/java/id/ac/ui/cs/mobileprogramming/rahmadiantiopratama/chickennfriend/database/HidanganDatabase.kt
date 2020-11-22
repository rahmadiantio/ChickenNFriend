package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao.HidanganDAO
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity

@Database(entities = [HidanganEntity::class], version = 1, exportSchema = false)
abstract class HidanganDatabase: RoomDatabase(){
    abstract fun hidanganDao(): HidanganDAO
    companion object{
        @Volatile
        private var INSTANCE: HidanganDatabase? = null

        fun getDatabase(context: Context): HidanganDatabase{
            val instanceSementara = INSTANCE
            if(instanceSementara != null){
                return instanceSementara
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, HidanganDatabase::class.java,
                    "hidangan_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}