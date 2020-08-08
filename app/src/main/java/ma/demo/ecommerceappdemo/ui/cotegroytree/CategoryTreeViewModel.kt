package ma.demo.ecommerceappdemo.ui.cotegroytree

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ma.demo.ecommerceappdemo.model.Category

class CategoryTreeViewModel(application: Application) : AndroidViewModel(application) {
    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)

     var categoryTreeList: ArrayList<Category> = ArrayList()

}