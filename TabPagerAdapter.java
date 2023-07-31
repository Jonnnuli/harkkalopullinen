package app.lutemon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.lutemon.fragments.FragmentA;
import app.lutemon.fragments.FragmentB;
import app.lutemon.fragments.FragmentC;
import app.lutemon.fragments.FragmentD;

public class TabPagerAdapter extends FragmentStateAdapter{


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new FragmentA(); //Koti
            case 1:
                return new FragmentB(); //Treeni
            case 2:
                return new FragmentC(); //Taistelu
            case 3:
                return new FragmentD(); //Kuolleet
            default:
                return new FragmentA();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
