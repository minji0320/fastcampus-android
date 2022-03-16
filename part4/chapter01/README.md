# Part4 - Chapter01 유튜브

## 학습 내용
- [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout?hl=ko)
    - ConstraintLayout 라이브러리의 일부 (서브 클래스)
    - 레이아웃 전환과 UI 이동, 크기 조절 및 애니메이션에 사용
- [ExoPlayer](https://exoplayer.dev/hello-world.html)
    - Google의 Android SDK와 별도로 배포되는 오픈소스 프로젝트
    - 오디오 및 동영상 재생 가능
    - 오디오 및 동영상 재생 관련 강력한 기능들 포함
    - 유튜브 앱에서 사용하는 라이브러리

## 구현 내용
- Retrofit을 이용하여 영상 목록을 받아와 구성
- MotionLayout을 이용하여 유튜브 영상 플레이 화면 전환 애니메이션 구현
- 영상 목록 클릭 시 ExoPlayer를 이용하여 영상 재생 가능

<img src="https://user-images.githubusercontent.com/43491968/158613482-b8a62399-1cd4-410e-88dc-97e185c8c475.png" height="450" alt="초기 홈 화면">
<img src="https://user-images.githubusercontent.com/43491968/158613545-0592c586-2ce4-430f-a73a-36323a81adca.png" height="450" alt="동영상 재생 화면">
<img src="https://user-images.githubusercontent.com/43491968/158613627-63943f69-b91c-458c-ae1a-45b0ae308754.png" height="450" alt="동영상 재생 화면을 아래로 내렸을 때의 화면">