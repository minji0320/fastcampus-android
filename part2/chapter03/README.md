# Part2 - Chapter03 비밀 다이어리

## 학습 내용
- ConstraintLayout, Custom Font
- Handler
- SharedPreference
- Theme
- AlertDialog
- Kotlin 문법 : android-ktx로 SharedPreference 사용하기 (Kotlin Android Extension)

## 구현 내용
- 다이어리처럼 UI 꾸며보기
- 비밀번호 저장/변경 기능
- 다이어리 내용을 앱이 종료되더라도 기기에 저장하는 기능
- 메인 화면
  - NumberPicker : 비밀번호 입력
  - Open Button : 비밀번호 일치 시 다이어리 내용 open
  - Change Password Button : 비밀번호 변경 버튼, 비밀번호는 SharedPreference를 이용하여 저장
- 다이어리 화면
  - SharedPreference에 저장된 내용 불러옴
  - 입력 멈추고 0.5초 후 현재 내용 SharedPreference에 저장

<img src="https://user-images.githubusercontent.com/43491968/152650955-cd3dc9af-b4c9-4bb2-914c-868e842f53cf.png" height="450">
<img src="https://user-images.githubusercontent.com/43491968/152650957-ceacd682-46a6-4a3e-856f-7f7d702fc106.png" height="450">
