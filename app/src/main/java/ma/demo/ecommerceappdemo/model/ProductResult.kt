package ma.demo.ecommerceappdemo.model


/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */

class ProductResult(
    val cachingForbidden: String,
    val displayName: String,
    val topShop: String,
    val resultCount: String,
    val productResults: ArrayList<Product>
)