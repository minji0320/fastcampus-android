package fastcampus.aop.part3.chapter03

import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

        initOnOffButton()
        initChangeAlarmTimeButton()

        val model = fetchDataFromSharedPreferences()
        renderView(model)
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
                renderView(model)

                // 기존 알람 삭제
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()
        }
    }

    private fun saveAlarmModel(hour: Int, minute: Int, onOff: Boolean = false): AlarmDisplayModel {
        val model = AlarmDisplayModel(hour, minute, onOff)
        val sharePreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        with(sharePreferences.edit()) {
            putString(ALARM_KEY, model.makeDataForDB())
            putBoolean(ON_OFF_KEY, model.onOff)
            commit()
        }
        return model
    }

    private fun fetchDataFromSharedPreferences(): AlarmDisplayModel {
        val sharePreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val timeDBValue = sharePreferences.getString(ALARM_KEY, "9:30") ?: "9:30"
        val onOffDBValue = sharePreferences.getBoolean(ON_OFF_KEY, false)
        val alarmData = timeDBValue.split(":")

        val alarmModel = AlarmDisplayModel(
            hour = alarmData[0].toInt(),
            minute = alarmData[1].toInt(),
            onOff = onOffDBValue
        )

//        val pendingIntent = PendingIntent.getBroadcast(
//            this,
//            ALARM_REQUEST_CODE,
//            Intent(this, AlarmReceiver::class.java),
//            PendingIntent.FLAG_NO_CREATE
//        )
//
//        // 보정 (예외 처리)
//        if ((pendingIntent == null) and alarmModel.onOff) {
//            // 알람은 꺼져있는데, 저장된 데이터는 on인 경우
//            alarmModel.onOff = false
//        } else if ((pendingIntent != null) and !alarmModel.onOff) {
//            // 알람은 켜져있는데, 저장된 데이터는 off인 경우 : 알람 취소
//            pendingIntent.cancel()
//        }

        return alarmModel
    }

    private fun renderView(model: AlarmDisplayModel) {
        findViewById<TextView>(R.id.ampmTextView).apply {
            text = model.ampmText
        }
        findViewById<TextView>(R.id.timeTextView).apply {
            text = model.timeText
        }

        onOffButton.apply {
            text = model.onOffText
            tag = model
        }
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "time"
        private const val ALARM_KEY = "alarm"
        private const val ON_OFF_KEY = "onOff"
        private const val ALARM_REQUEST_CODE = 1000
    }
}