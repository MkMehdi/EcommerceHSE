package ma.demo.ecommerceappdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ma.demo.ecommerceappdemo.db.entity.ProductEntity


/**
 * Created by Elmehdi Mellouk on 8/8/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    fun insertProduct(productEntity: ProductEntity): Long

    @Delete
    fun deleteProduct(productEntity: ProductEntity): Int

    @Query("select * from product")
    suspend fun getProduct(): List<ProductEntity>?

}