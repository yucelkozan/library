package com.kozan.utils

/*object GuideViewUtil {

    var guideView: GuideView? = null

    fun show(context: Context, view: View, tag: String) {

        val key = "${tag}_displayed"
        val sharedPreferences = context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
        val isShowcaseDisplayed =
            sharedPreferences.getBoolean(key, false)
        if (isShowcaseDisplayed) return
        else {

            sharedPreferences.edit().putBoolean(key, true).apply()
            guideView = GuideView.Builder(context)
                .setContentText(context.getString(R.string.you_can_change_the_country_you_have_selected_here))
                .setGravity(Gravity.auto) //optional
                .setDismissType(DismissType.anywhere) //optional - default DismissType.targetView
                .setTargetView(view)
                .setContentTextSize(16) //optional
                .build()
            guideView?.show()
        }
    }

    fun dismiss(){
        guideView?.dismiss()
    }
}*/

