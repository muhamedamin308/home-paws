package com.example.homepaws.features.onboarding.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class ViewPagerAdapter(
    private val list: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment = list[position]
}