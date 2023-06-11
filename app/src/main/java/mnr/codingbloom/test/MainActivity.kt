package mnr.codingbloom.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import mnr.codingbloom.test.Screens.Home
import mnr.codingbloom.test.Screens.Splash
import mnr.codingbloom.test.ui.theme.TestProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavigationManager()

                }
            }
        }
    }
}

@Composable
fun NavigationManager() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Splash){

        composable(Splash){

            SplashScreen(navController = navController)
        }

        composable(Home){

            HomeScreen()
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen( navController: NavController) =

    Box( modifier =
    Modifier.fillMaxSize(),
    Alignment.Center

) {

        val scale = remember {
            androidx.compose.animation.core.Animatable(0.0f)
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )

            delay(1000)

            navController.popBackStack()
            navController.navigate(Home)
        }

    Image(
        modifier = Modifier
            .scale(scale = scale.value),
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "splash logo"
    )
}


@Composable
fun HomeScreen(){

    Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {

        Text(text = "Home Screen", fontSize = 34.sp, fontWeight = FontWeight.Bold)
    }
}


object Screens {
    const val Splash = "Splash"
    const val Home = "Home"
}