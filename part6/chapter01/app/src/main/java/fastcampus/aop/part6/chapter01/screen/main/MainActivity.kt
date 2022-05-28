package fastcampus.aop.part6.chapter01.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fastcampus.aop.part6.chapter01.R
import fastcampus.aop.part6.chapter01.databinding.ActivityMainBinding
import fastcampus.aop.part6.chapter01.screen.main.home.HomeFragment
import fastcampus.aop.part6.chapter01.screen.main.like.RestaurantLikeListFragment
import fastcampus.aop.part6.chapter01.screen.main.my.MyFragment
import fastcampus.aop.part6.chapter01.util.event.MenuChangeEventBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.menu_like -> {
                showFragment(RestaurantLikeListFragment.newInstance(),
                    RestaurantLikeListFragment.TAG)
                true
            }
            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }

        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}