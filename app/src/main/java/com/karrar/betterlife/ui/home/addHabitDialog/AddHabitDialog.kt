package com.karrar.betterlife.ui.home.addHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogAddHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent

class AddHabitDialog : BaseDialogFragment<DialogAddHabitBinding, AddHabitViewModel>() {
    override val viewModelClass: Class<AddHabitViewModel> = AddHabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_add_habit

    override fun setup() {
        customizeWindowSize()
        onAddHabitDone()
        cancelDialogOfHabit()
    }

    private fun customizeWindowSize() {
        setWidthPercent(90)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun onAddHabitDone() {
        viewModel.isAddHabit.observe(this, EventObserve {
            if (it) {
                dismiss()
                Toast.makeText(this.context, R.string.toast_success, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun cancelDialogOfHabit() {
        viewModel.isCancel.observe(this, EventObserve {
            if (it) {
                dismiss()
            }
        })
    }

}