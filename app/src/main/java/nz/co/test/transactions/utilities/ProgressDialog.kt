package nz.co.test.transactions.utilities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import nz.co.test.transactions.R

class ProgressDialog // constructor of dialog class  
// with parameter activity 
internal constructor(
    private val activity: Activity
) {
    private var dialog: Dialog? = null

    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loader, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog!!.show()
    }

    // dismiss method 
    fun dismissDialog() {
        dialog!!.dismiss()
    }
}