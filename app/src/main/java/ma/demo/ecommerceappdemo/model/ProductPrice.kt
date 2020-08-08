package ma.demo.ecommerceappdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */


@Parcelize
class ProductPrice(
    val price: String,
    val referencePrice: String,
    val currency: String): Parcelable