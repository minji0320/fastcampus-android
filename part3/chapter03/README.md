# Part3 - Chapter03 알람앱

## 학습 내용
- Notification (파트3 챕터1 복습)
- Broadcast receiver
- Background 작업
    - Immediate tasks(즉시 실행해야하는 작업)
        - Thread
        - Handler
        - Kotlin coroutines
    - Deferred task (지연된 작업)
        - WorkManager
    - Exact tasks (정시에 실행해야 하는 작업)
        - AlarmManager
- AlarmManager
    - Real Time (실제 시간)으로 실행시키는 방법
    - Elapsed Time (기기 부팅 후 얼마나 지났는지)으로 실행시키는 방법

## 구현 내용
- 지정된 시간에 알람 울리게 할 수 있음
- 지정된 시간 이후에는 매일 같은 시간에 반복하여 알람이 울리게 할 수 있음

<img src="https://user-images.githubusercontent.com/43491968/155553627-096db8cc-69d4-4629-9799-eda33ec94b85.png" height="450">
<img src="https://user-images.githubusercontent.com/43491968/155553905-e5ee15e8-44d9-411d-84af-c22930c94a82.png" height="450">
<img src="https://user-images.githubusercontent.com/43491968/155553633-08cecb80-07b3-4e51-9c9e-09310ee14f5c.png" height="450">