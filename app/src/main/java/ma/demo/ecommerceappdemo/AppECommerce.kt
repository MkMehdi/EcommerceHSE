package ma.demo.ecommerceappdemo

import android.app.Application
import android.content.Context
import ma.demo.ecommerceappdemo.api.Hse24Api
import ma.demo.ecommerceappdemo.db.AppDatabase
import ma.demo.ecommerceappdemo.db.DataRepository


/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

class AppECommerce:Application(){

    companion object {
        var appContext: Context? = null
    }

    private val api by lazy {
        Hse24Api.create()
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }

     fun getHse24Api(): Hse24Api = api

    private fun getDatabase(): AppDatabase? {
        return AppDatabase.getInstance(appContext!!)
    }

    fun getRepository(): DataRepository? {
        return DataRepository(getDatabase())
    }
}