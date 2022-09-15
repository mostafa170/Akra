package com.kamel.akra.app.utilsView

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyBottomSheet(private val context: Context) {

    fun getDialogInstance(layout: Int): BottomSheetDialog =BottomSheetDialog(context).apply{
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(layout)

        return bottomSheetDialog
    }
}