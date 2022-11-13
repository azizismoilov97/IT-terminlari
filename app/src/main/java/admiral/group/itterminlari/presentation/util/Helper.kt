package admiral.group.itterminlari.presentation.util

import admiral.group.itterminlari.R
import admiral.group.itterminlari.presentation.constant.ConnectionConst
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object Helper {

    /**
     * Shares note as a text
     */
    fun shareNote(context: Context, note: String) {
        Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, note)
            context.startActivity(Intent.createChooser(this, context.getString(R.string.choose)))
        }
    }

    /**
     * Navigates the user to connect us via Telegram
     */
    fun connectViaTelegram(context: Context) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ConnectionConst.SUPPORT_TELEGRAM)))
    }

    /**
     * Navigates the user to Play Market
     */
    fun goPlayMarket(context: Context) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ConnectionConst.PLAY_STORE_PAGE)))
    }

    /**
     * Navigates the user to rate the app
     */
    fun rateApp(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${ConnectionConst.PLAY_STORE_DETAIL_PAGE}$appPackageName")))
        }
    }
}