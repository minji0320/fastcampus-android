package fastcampus.aop.part4.chapter02

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class PlayerFragment : Fragment(R.layout.fragment_player) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        // 재사용성을 위해 생성
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}