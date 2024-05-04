package app.kitabcha.ui.loginScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.kitabcha.data.User
import org.json.JSONObject.NULL
class login_viewModel {
    var name_of_User:String="0"
    var password_received:String="0"
    fun getPassword(name_received:String,password_coming:String)
    {
        name_of_User = name_received
        password_received=password_coming

        var user = User.create()

    }

}





