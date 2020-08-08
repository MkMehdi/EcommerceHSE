package ma.demo.ecommerceappdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

@Parcelize
class Product(
    val sku: String,
    val nameShort: String,
    val status: String,
    val brandNameLong: String,
    val productPrice: ProductPrice,
    val title:String,
    val longDescription:String
) : Parcelable