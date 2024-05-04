package app.kitabcha

import android.os.Bundle
import android.widget.Button
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import app.kitabcha.ui.theme.KitabchaTheme
import com.mkrdeveloper.viewmodeljetpack.MainViewModel
import com.mkrdeveloper.viewmodeljetpack.app.kitabcha.ui.loginScreen.login_viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val nav = rememberNavController()
            val loginViewModel=ViewModelProvider(this)[MainViewModel::class.java]// login_viewModel by viewModels()
            KitabchaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Login_main(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun Login_main(login: MainViewModel)
{
    var name by remember { mutableStateOf("") } // name of user
    var password by remember { mutableStateOf("") } // name of user
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Alignment
    ){
        Text(
            text = "Username Field",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        userNameField(name,{name=it},Modifier.padding(bottom=20.dp).fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Password Field",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        passwordField(password,{password=it},Modifier.padding(bottom=20.dp).fillMaxWidth())
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {login.getUserData(name,password)})
        {
            Text(text="Login")
        }
    }


}

@Composable
fun userNameField(value_coming:String, onvalueChange: (String)->Unit,  modifier: Modifier = Modifier)
{
    val lbl: String = "Enter username"
    var name by remember { mutableStateOf("") } // var amountInput: MutableState<String> = mutableStateOf("0")
    TextField(label={Text(lbl)},
        singleLine=  true,
        value= value_coming,
        onValueChange = onvalueChange,
        modifier=Modifier
    )
}

@Composable
fun passwordField(value_coming:String, onvalueChange: (String)->Unit,  modifier: Modifier = Modifier)
{
    val lbl: String = "Enter username"
    var password by remember { mutableStateOf("") } // var amountInput: MutableState<String> = mutableStateOf("0")
    TextField(label={Text(lbl)},
        singleLine=  true,
        value= value_coming,
        onValueChange = onvalueChange,
        modifier=Modifier
    )
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KitabchaTheme {
//        Greeting("Android")
//    }
//}