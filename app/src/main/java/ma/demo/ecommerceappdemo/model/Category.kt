package ma.demo.ecommerceappdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

@Parcelize
class Category(val categoryId:String,
               val displayName:String,
               val resultCount:String,
               val children:ArrayList<Category>
): Parcelable