package com.karrar.betterlife.ui.home

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.databinding.ItemHabitBinding
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.asHabitWithType

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun setup() {
        setupChipGroupDynamically()
        navigateToStatistics()
        navigateToAddHabitDialog()
    }

    private fun navigateToAddHabitDialog() {
        viewModel.navigateAddHabit.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToStatistics()
                )
        }
    }

    private fun navigateToStatistics() {
        viewModel.navigateShowStatistics.observe(viewLifecycleOwner) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStatistics())
        }
    }

    private fun setupChipGroupDynamically() {
        viewModel.habits.observe(this) {
            binding.chipGroupHabit.removeAllViews()
            it?.let {
                it.forEach { habit ->
                    binding.chipGroupHabit.addView(createChip(habit))
                }
            }
        }
    }

    private fun createChip(item: Habit): View {
        val chipBinding: ItemHabitBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_habit,
            binding.chipGroupHabit,
            false
        )
        chipBinding.viewModel = viewModel
        chipBinding.item = item.asHabitWithType()
        return chipBinding.root
    }

}