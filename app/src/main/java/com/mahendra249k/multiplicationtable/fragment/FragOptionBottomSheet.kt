package com.mahendra249k.multiplicationtable.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mahendra249k.multiplicationtable.R

open class FragOptionBottomSheet : BottomSheetDialogFragment() {

    var mListener: OnMultiplicationClickListener? = null

    open fun setMultiplicationClickListener(mListener: OnMultiplicationClickListener?) {
        this.mListener = mListener
    }

    interface OnMultiplicationClickListener {
        fun OnMultiplicationClick(mIndex: Double)
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, i: Int) {
        super.setupDialog(dialog, i)
        val inflate = View.inflate(context, R.layout.frag_bottom_sheet, null)
        dialog.setContentView(inflate)
        (inflate.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

        val editextIndex = inflate.findViewById<EditText>(R.id.editextIndex)
        val textMultiplication = inflate.findViewById<TextView>(R.id.textMultiplication)

        val behavior =
            ((inflate.parent as View).layoutParams as CoordinatorLayout.LayoutParams).behavior

        textMultiplication.setOnClickListener {
            val index = editextIndex.text.toString()
            if (TextUtils.isEmpty(index)) {
                editextIndex.error = "Required"
                return@setOnClickListener
            }
            mListener?.OnMultiplicationClick(index.toDouble())
            dismiss()
        }
    }

}