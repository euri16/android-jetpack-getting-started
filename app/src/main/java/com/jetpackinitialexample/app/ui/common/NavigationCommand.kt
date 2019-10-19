package com.jetpackinitialexample.app.ui.common

import androidx.navigation.NavDirections

sealed class NavigationCommand {
  data class To(val directions: NavDirections): NavigationCommand()
  object Back: NavigationCommand()
  data class BackTo(val destinationId: Int): NavigationCommand()
}