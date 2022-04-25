# Part5 - Chapter04 SNS 글 업로드 기능 개선하기

## 학습 내용

- Firebase :  Realtime Database, Storage
- Coroutines
- CameraX

## 구현 내용

- 기존 중고거래 앱 UI 수정 (SNS 컨셉)
- 사진 첨부 방식 두 가지 제공
    - 카메라 촬영 후 첨부
    - 갤러리 이미지 선택 후 첨부
- 이미지 리스트 첨부 및 여러개 동시 업로드
- 업로드 중 에러 발생 대응

## App 화면

| 초기 피드 목록 화면                                                                                                                                   | 피드 등록 화면                                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://user-images.githubusercontent.com/43491968/165096736-e0d94efb-64a8-479d-a6bd-6ff281f80c09.png" height="450" alt="초기 피드 목록"> | <img src="https://user-images.githubusercontent.com/43491968/165096740-1a270635-f350-4194-84c7-161d957f4d0f.png" height="450" alt="피드 등록 페이지"> |

| 사진 첨부 방식 - 갤러리 & 카메라                                                                                                                                      | 갤러리 사진 첨부하기                                                                                                                                      |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://user-images.githubusercontent.com/43491968/165096746-88389da6-12f7-4b16-b849-ecfb44b2679c.png" height="450" alt="사진 첨부 방식 - 갤러리 & 카메라"> | <img src="https://user-images.githubusercontent.com/43491968/165096747-368f97a1-350a-4961-877f-644b4530e1ea.png" height="450" alt="갤러리 사진 첨부하기"> |

| 카메라 촬영 화면                                                                                                                                      | 촬영한 사진 리스트 화면                                                                                                                                      |
|------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://user-images.githubusercontent.com/43491968/165096764-6e5878eb-ab4e-4654-9985-d553970dd31e.png" height="450" alt="카메라 촬영 화면"> | <img src="https://user-images.githubusercontent.com/43491968/165096782-03148b93-3bc0-437e-8142-52c6a88272b6.png" height="450" alt="촬영한 사진 리스트 화면"> |

| 사진 첨부 결과 화면                                                                                                                                      | 피드 등록 완료 이후의 피드 목록 화면                                                                                                                                      |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://user-images.githubusercontent.com/43491968/165096801-a705e997-a7d1-4631-8db5-ef8c52dba6a5.png" height="450" alt="사진 첨부 결과 화면"> | <img src="https://user-images.githubusercontent.com/43491968/165096824-4846e7d7-dbb6-4ec1-8f4d-77aaada5e1c1.png" height="450" alt="피드 등록 완료 이후의 피드 목록 화면"> |