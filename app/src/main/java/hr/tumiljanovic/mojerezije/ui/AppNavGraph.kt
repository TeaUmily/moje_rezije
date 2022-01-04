package hr.tumiljanovic.mojerezije.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import hr.tumiljanovic.mojerezije.common.constants.*
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.ui.AppDestinations.HOME_ROUTE
import hr.tumiljanovic.mojerezije.ui.billsDisplay.BillsDisplayScreen
import hr.tumiljanovic.mojerezije.ui.home.HomeScreen
import hr.tumiljanovic.mojerezije.ui.billForm.BillFormScreen
import hr.tumiljanovic.mojerezije.ui.reminderSettings.ReminderSettingsScreen
import hr.tumiljanovic.mojerezije.ui.utilityDetails.UtilityDetailsScreen

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val UTILITY_DETAILS_ROUTE = "utilityDetails"
    const val BILL_FORM_ROUTE = "billForm"
    const val REMINDER_SETTINGS_ROUTE = "reminderSettings"
    const val BILLS_DISPLAY_ROUTE = "billsDisplay"
}

@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE
) {
    val actions = remember(navController) { NavigationActions(navController) }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(HOME_ROUTE) {
            HomeScreen(
                navigateToBillForm =  actions.navigateToBillForm,
                navigateToNotificationSettings = actions.navigateToReminderSettings,
                navigateToUtilityDetails = actions.navigateToUtilityDetails
            )
        }
        composable("${AppDestinations.UTILITY_DETAILS_ROUTE}/{$UTILITY_NAME_KEY}") {
            UtilityDetailsScreen(
                onBackPressedCallback = actions.upPress,
                navigateToBillsDisplay = actions.navigateToBillsDisplay,
                navigateToBillForm =  actions.navigateToBillForm
            )
        }
        composable("${AppDestinations.BILL_FORM_ROUTE}/{$PRESELECTED_UTILITY_KEY}/{$BILL_ID_KEY}") {
            BillFormScreen(
                upPressCallback = actions.upPress,
            )
        }
        composable(AppDestinations.REMINDER_SETTINGS_ROUTE) {
            ReminderSettingsScreen(
                upPressCallback = actions.upPress
            )
        }
        composable("${AppDestinations.BILLS_DISPLAY_ROUTE}/{$UTILITY_NAME_KEY}/{$DISPLAY_PAID_BILLS_KEY}") {
            BillsDisplayScreen(
                upPressCallback = actions.upPress,
                navigateToBillForm = actions.navigateToBillForm
            )
        }
    }
}

class NavigationActions(navController: NavHostController) {
    val navigateToBillForm: (String, String) -> Unit = { preselectedUtility, billId ->
        navController.navigate("${AppDestinations.BILL_FORM_ROUTE}/$preselectedUtility/$billId")
    }
    val navigateToReminderSettings: () -> Unit = {
        navController.navigate(AppDestinations.REMINDER_SETTINGS_ROUTE)
    }
    val navigateToUtilityDetails: (Utility) -> Unit = { utility ->
        navController.navigate("${AppDestinations.UTILITY_DETAILS_ROUTE}/${utility.name}")
    }
    val navigateToBillsDisplay: (Utility, Boolean) -> Unit = { utility, displayPaidBills ->
        navController.navigate("${AppDestinations.BILLS_DISPLAY_ROUTE}/${utility.name}/$displayPaidBills")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}