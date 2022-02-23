package fastcampus.aop.part3.chapter03

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    private val onOffButton: Button by lazy {
        findViewById(R.id.onOffButton)
    }

    private val changeAlarmTimeButton: Button by lazy {
        findViewById(R.id.changeAlarmTimeButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // step 0 뷰를 초기화 해주기
        initOnOffButton()
        initChangeAlarmTimeButton()

        // step 1 데이터 가져오기

        // step 2 뷰에 데이터를 그려주기
    }

    private fun initOnOffButton() {
        onOffButton.setOnClickListener {
            // 데이터를 확인을 한다
            // 온오프에 따라 작업을 처리한다
            // 오프 -> 알람을 제거
            // 온 -> 알람을 등록
            // 데이터 저장
        }
    }

    private fun initChangeAlarmTimeButton() {
        changeAlarmTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { picker, hour, minute ->
                val model = saveAlarmModel(hour, minute)
                // 데이터 저장
                // 뷰 업데이트
                // 기존 알람 삭제
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()
        }
    }

    private fun saveAlarmModel(hour: Int, minute: Int, onOff: Boolean = false): AlarmDisplayModel {
        val model = AlarmDisplayModel(hour, minute, onOff)
        val sharePreferences = getSharedPreferences("time", Context.MODE_PRIVATE)
        with(sharePreferences.edit()) {
            putString("alarm", model.makeDataForDB())
            putBoolean("onOff", model.onOff)
            commit()
        }
        return model
    }
}