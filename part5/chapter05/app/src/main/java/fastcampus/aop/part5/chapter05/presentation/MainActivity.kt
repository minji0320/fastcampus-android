package fastcampus.aop.part5.chapter05.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import fastcampus.aop.part5.chapter05.R
import fastcampus.aop.part5.chapter05.databinding.ActivityMainBinding
import fastcampus.aop.part5.chapter05.extension.toGone
import fastcampus.aop.part5.chapter05.extension.toVisible

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainNavigationHostContainer) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    // actionBar와 NavController 연동을 위해 사용
    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        // actionBar와 NavController 연동을 위해 사용
        // 타이틀이 바뀜, 네비게이션 백 버튼이 자동으로 바
        setupActionBarWithNavController(navigationController)
    }

    private fun bindViews() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.station_arrivals_dest) {
                binding.toolbar.toVisible()
            } else {
                binding.toolbar.toGone()
            }
        }
    }
}