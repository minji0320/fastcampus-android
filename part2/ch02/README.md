# Part2 - Chapter02 로또 번호 추첨기

## 학습 내용
- ConstraintLayout, NumberPicker, TextView, Button
- [Shape Drawable](https://developer.android.com/guide/topics/resources/drawable-resource?hl=ko#Shape)
- Kotlin 문법 : apply, when, Random, Collection(Set, List), 람다 함수

## 구현 내용
- 0~5개까지 수동 선택 가능하도록 구현
- 수동 선택한 번호를 제외한 나머지 번호는 랜덤으로 표시하기
- 메인 화면
    - NumberPicker : 수동 번호 선택
    - 번호 추가하기 : NumberPicker로 선택한 번호 추가
    - 초기화 : 모든 번호 초기화
    - 자동 생성 시작 : 수동으로 선택한 번호 이외의 숫자 중복없이 랜덤하게 생성

<img src="https://user-images.githubusercontent.com/43491968/152492690-96627222-85c3-4622-ad4b-9fb2ce5925ec.png" height="450">
<img src="https://user-images.githubusercontent.com/43491968/152492731-119b0262-dec1-4643-82b6-84391849e77f.png" height="450">
<img src="https://user-images.githubusercontent.com/43491968/152492801-7279c373-ef36-45aa-9c29-ad0f1d94df42.png" height="450">


