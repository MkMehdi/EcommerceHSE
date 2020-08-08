package ma.demo.ecommerceappdemo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Elmehdi Mellouk on 8/8/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

@Entity(tableName = "product")
class ProductEntity(
    @PrimaryKey
    val sku: String,
    val nameShort: String = "",
    val status: String = "",
    val brandNameLong: String = "",
    val price:String = "",
    val currency:String = "",
    val title:String,
    val longDescription:String = ""
)