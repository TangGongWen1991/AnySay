package com.maruko.anysay

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maruko.anysay.model.BottomItemData
import com.maruko.anysay.ui.theme.AnySayTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnySayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainView(){
    val navController = rememberNavController()
     val menuData = listOf(
        BottomItemData("首页", Icons.Filled.Home),
        BottomItemData("关注", Icons.Filled.Favorite),

        BottomItemData("我的", Icons.Filled.Person)
    )
//    Scaffold(topBar = { TopbarView()}, bottomBar = { BottombarView(navController,menuData)}){
//        ContentView(navController,menuData)
//    }
    Scaffold(topBar = { TopbarView() }, bottomBar = {BottombarView(navController = navController, items = menuData)}) {
        ContentView(navController = navController, items = menuData)
    }
}

@Composable
fun TopbarView(){
    Row (
        Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(text ="首页")
    }
}

@Composable
fun ContentView(navController:NavHostController,items:List<BottomItemData>){

    NavHost(navController = navController, startDestination = items[0].name, modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        //声明名为MainPage的页面路由
        composable(items[0].name){
            //页面路由对应的页面组件
            MainPage()
        }
        composable(items[1].name){
            FocusPage()
        }
        composable(items[2].name){
            MinePage()
        }
    }

}

@Composable
fun MinePage() {
    Row {
        Text(text = "主页测试")

    }
}

@Composable
fun FocusPage() {
    Row {
        Text(text = "关注测试测试")

    }
}

@Composable
fun MainPage() {
    Row {
        Modifier
            .fillMaxSize()
            .background(Color.Black)
        Text(text = "主页测试")

    }

}



@Composable
fun BottombarView(navController:NavHostController,items:List<BottomItemData>){
    val currentSelect = remember {
        mutableIntStateOf(0)
    }


    NavigationBar(containerColor = Color.White, contentColor = Color.White) {
        items.forEachIndexed { index, bottomItemData ->
            NavigationBarItem(

                selected = index == currentSelect.intValue,
                onClick = {
                    currentSelect.intValue = index
                    navController.popBackStack()
                    navController.navigate(items[index].name)
                },
                icon = {
                    Icon(
                        imageVector = bottomItemData.icon,
                        contentDescription = "点击按钮"
                    )
                },

                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Blue
                    , selectedTextColor = Color.Blue
                    , indicatorColor = Color.White
                , unselectedIconColor = Color.Black, unselectedTextColor = Color.Black),
                label = {
                    Text(
                        fontSize = 16.sp,
                        text = (bottomItemData.name)
                    )
                },
            )
        }
    }
}




