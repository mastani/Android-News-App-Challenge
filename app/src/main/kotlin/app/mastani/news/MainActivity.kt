package app.mastani.news

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.mastani.news.archive.archiveScreen
import app.mastani.news.designsystem.bottomnavigation.TabBarItem
import app.mastani.news.designsystem.bottomnavigation.TabView
import app.mastani.news.designsystem.component.DarkModeSwitch
import app.mastani.news.designsystem.theme.AndroidChallengeTheme
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.detail.detailScreen
import app.mastani.news.home.homeScreen
import app.mastani.news.navigation.Screen
import app.mastani.news.search.searchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()

            NewsApp(
                isDarkTheme = isDarkTheme ?: false,
                toggleDarkTheme = {
                    val appTheme = isDarkTheme ?: false
                    viewModel.setAppTheme(!appTheme)
                }
            )
        }
    }
}

@Composable
fun NewsApp(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    toggleDarkTheme: () -> Unit = {}
) {
    AndroidChallengeTheme(
        darkTheme = isDarkTheme
    ) {
        val navController = rememberNavController()
        BackHandler {
            navController.popBackStack()
        }

        // Bottom tab items
        val tabBarItems = listOf(
            TabBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            TabBarItem(
                title = "Search",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            ),
            TabBarItem(
                title = "Archive",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.Favorite
            ),
        )

        Scaffold(
            topBar = {
                // Theme toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .padding(
                            start = 12.dp,
                            end = 24.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.app_name),
                        style = Typography.headlineSmall
                    )

                    DarkModeSwitch(
                        checked = isDarkTheme,
                        onCheckedChanged = {
                            toggleDarkTheme()
                        }
                    )
                }
            },
            bottomBar = {
                // Bottom navigation
                TabView(tabBarItems, navController)
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                homeScreen(
                    route = Screen.Home.route,
                    navController = navController
                )

                searchScreen(
                    route = Screen.Search.route,
                    navController = navController
                )

                archiveScreen(
                    route = Screen.Archive.route,
                    navController = navController
                )

                detailScreen(
                    navController = navController
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun NewsAppPreview() {
    NewsApp()
}