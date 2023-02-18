package com.pragma.deeplinkspoc.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.pragma.deeplinkspoc.Greeting
import com.pragma.deeplinkspoc.showParameterFromDeeplink

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){

        composable(route = "home") {
            Greeting(name = "This is a How to deeplink demo")
        }

        composable(
            route = "detail/{id}" ,
            deepLinks = listOf(
                navDeepLink {
                    label = "intent"
                    uriPattern = "appone://cualquier.direccion.co/detail/{id}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = "without id"
                }
            )
        ) {
            // TODO : get parameter from deeplink
            entry ->
            var data = entry.arguments?.getString("id")
            if (data != null) {
                showParameterFromDeeplink(deeplinkParameter = data)
            }
        }

    }
}