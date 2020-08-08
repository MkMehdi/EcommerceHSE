package ma.demo.ecommerceappdemo.db

import ma.demo.ecommerceappdemo.db.entity.ProductEntity


/**
 * Created by Elmehdi Mellouk on 8/8/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */


class DataRepository(database: AppDatabase?) {

    private var sInstance: DataRepository? = null

    private var mDatabase: AppDatabase? = database

    /*init {
        mObservableAlbums?.addSource(mDatabase?.albumDao()?.getAlbums()!!) {
            mObservableAlbums?.postValue(it)
        }
    }*/

    fun getInstance(database: AppDatabase): DataRepository? {
        if (sInstance == null) {
            synchronized(DataRepository::class.java) {
                if (sInstance == null) {
                    sInstance = DataRepository(database)
                }
            }
        }
        return sInstance
    }


    suspend fun getProducts(): List<ProductEntity>? {
        return mDatabase?.productDao()?.getProduct()
    }

    fun deleteProduct(productEntity: ProductEntity): Int? {
        return mDatabase?.productDao()?.deleteProduct(productEntity)
    }

    fun addProduct(productEntity: ProductEntity): Long? {
        return mDatabase?.productDao()?.insertProduct(productEntity)
    }
}