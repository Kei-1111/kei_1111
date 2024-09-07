package org.example.project.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.ui.profile.ProfileScreen
import org.example.project.ui.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(
            route = Screen.Splash.route,
            popEnterTransition = { fadeIn(animationSpec = tween(2000), initialAlpha = 0.1f) },
            popExitTransition = { fadeOut(animationSpec = tween(2000)) }
        ) {
            SplashScreen(
                toProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(
            route = Screen.Profile.route,
            popEnterTransition = { fadeIn(animationSpec = tween(1000), initialAlpha = 0.1f) },
            popExitTransition = { fadeOut(animationSpec = tween(2000)) }
        ) {
            ProfileScreen()
        }
    }
}

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Profile : Screen("profile")
}