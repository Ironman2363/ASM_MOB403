package com.example.ph25533_asm.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ph25533_asm.FRAGMENT.CartFragment;
import com.example.ph25533_asm.FRAGMENT.FavouriteFragment;
import com.example.ph25533_asm.FRAGMENT.HomeFragment;
import com.example.ph25533_asm.FRAGMENT.SearchFragment;
import com.example.ph25533_asm.FRAGMENT.UserFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = CartFragment.newInstance();
                break;
            case 2:
                fragment = FavouriteFragment.newInstance();
                break;
            case 3:
                fragment = SearchFragment.newInstance();
                break;
            case 4:
                fragment = UserFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
