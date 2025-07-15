package com.itos.xplan.ui.Pages

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.itos.xplan.XPlan
import com.itos.xplan.XPlan.Companion.app
import com.itos.xplan.ui.Pages.subassemblies.Opt.AutoBoostBotton
import com.itos.xplan.ui.Pages.subassemblies.Opt.ControlSystemUpdateButton
import com.itos.xplan.ui.Pages.subassemblies.Opt.HDButton
import com.itos.xplan.ui.Pages.subassemblies.Opt.OptButton
import com.itos.xplan.ui.theme.OriginPlanTheme
import com.itos.xplan.ui.viewmodel.AppViewModel
import com.itos.xplan.utils.DeviceUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptPage(viewModel: AppViewModel?) {
    val activity = XPlan.app
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "优化")
                },
                actions = {
//                IconButton(
//                    onClick = {
////                        SettingsDebug()
//
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.Build,
//                        contentDescription = "Debug"
//                    )
//                }
                    IconButton(
                        onClick = {
                            MaterialAlertDialogBuilder(app)
                                .setTitle("公告")
                                .setMessage(app.notice)
                                .setPositiveButton("了解") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "help"
                        )
                    }
                }
            )
        }) { innerPadding ->
        if (isLandscape(app)) {
            val scrollState = rememberScrollState()
            // 执行横屏时的操作
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
//                    .verticalScroll(scrollState)
//                    .padding(top = getStatusBarHeight().dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly // 将子项垂直居中
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    viewModel?.let {
                        OptButton(activity, it)
//                        ProcessLimitButton(it)
                        if (DeviceUtils.isMiuiOrVivo()) {
                            AutoBoostBotton()
                        }
                    }
                }

                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    viewModel?.let {
                        HDButton(it)
                        if (DeviceUtils.isMiui()) {
                            ControlSystemUpdateButton(it)
                        }
                    }
                }
            }
        } else {
            // 执行竖屏时的操作
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly // 将子项垂直居中
            ) {
                if (viewModel != null) {
                    OptButton(activity, viewModel)
                    if (DeviceUtils.isMiuiOrVivo()) {
                        AutoBoostBotton()
                    }
//                    ProcessLimitButton(it)
                    HDButton(viewModel)
                    if (DeviceUtils.isMiui()) {
                        ControlSystemUpdateButton(viewModel)
                    }
                }
            }
        }

    }
}

fun isLandscape(context: Context): Boolean {
    val configuration = context.resources.configuration
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun A() {
    OriginPlanTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OptPage(null)
        }
    }
}
