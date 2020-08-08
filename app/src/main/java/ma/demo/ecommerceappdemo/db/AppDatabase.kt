package ma.demo.ecommerceappdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ma.demo.ecommerceappdemo.db.dao.ProductDao
import ma.demo.ecommerceappdemo.db.entity.ProductEntity


/**
 * Created by Elmehdi Mellouk on 8/8/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */


@Database(entities = [(ProductEntity::class)], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "hse24.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}