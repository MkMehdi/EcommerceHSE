package ma.demo.ecommerceappdemo.ui.detailproduct

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ma.demo.ecommerceappdemo.model.Product

class DetailProductViewModel : ViewModel() {
    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)

    var idProduct:String = ""
    var product: Product? = null
}