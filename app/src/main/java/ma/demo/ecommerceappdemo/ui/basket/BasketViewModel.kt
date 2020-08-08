package ma.demo.ecommerceappdemo.ui.basket

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ma.demo.ecommerceappdemo.db.entity.ProductEntity
import ma.demo.ecommerceappdemo.model.Product

class BasketViewModel : ViewModel() {
    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)

    var productList: List<ProductEntity>? = ArrayList()
}