package ma.demo.ecommerceappdemo.ui.categories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ma.demo.ecommerceappdemo.model.Category

class CategoriesViewModel : ViewModel() {

    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)

    var categoryList: ArrayList<Category> = ArrayList()
}