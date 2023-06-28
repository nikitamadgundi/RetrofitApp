import com.example.retrofitapp.DataModel.Support
import com.example.retrofitapp.DataModel.User

data class UserListResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>,
    val support: Support
)




