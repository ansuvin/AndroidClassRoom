# AndroidClassRoom
* 학교 수업시간에 실습한 내용
* android 실습내용
* android studio 사용

### CookMap
 Google Map API를 이용한 지도 실습 파일
 
### Practice
 다양한 프로젝트를 진행하며 임시로 코딩한 파일

### jjapkaotalk
 커스텀 리스트뷰 실습 짭카오톡
  * 이미지
  * 이름
  * 한줄 소개

## chap_OO
### chap_05
 버튼 두개를 이용하여 이미지가 보이게, 안보이게 처리
 
### chap_07
##### app
 LayoutInflater를 통해 xml 코드를 view 객체로 처리

##### exercise01_optionmenu
 MenuInflater를 통해 xml에서 res>menu 폴더안에 option_menu.xml을 menu객체로 생성
 
##### exercise02_optionmenu
 자바에서 MenuInflater를 통해 menu 추가 + 서브 menu 추가
 
##### exercise03_contextmenu
 TextVie의 컨텍스트 메뉴를 설정
 
##### exercise_toast
 toast_layout.xml 을 View로 inflate 해서 나만에 Toast 만들기

##### exercise_dialog
 다양한 dialog(다이얼로그) 설정 방법
 * 버튼이 있는 대화상자
 * 기본 목록 대화상자
 * 라디오버튼 목록 대화상자
 * 라디오버튼 목록 대화상자 + 선택된 item Toast하기

##### practice7_1
 menu에 따라 배경색 바꾸기

### chap_08
##### app
 내장 메모리 파일 입출력 처리1 (FileOutputStream, FileInputStream) 영어만
 내장 메모리 파일 입출력 처리 2 (OutPutStreamWriter, InpPutStreamReader) 문자들

##### practice01_internalmenoryfileinputoutput
 내장 메모리에 파일을 쓰고 읽을 수 있는 EditText 1개 TextView 1개 Button 2개 사용
 * 내장 메모리 파일 입출력 처리1 (FileOutputStream, FileInputStream) 영어만
 * 내장 메모리 파일 입출력 처리 2 (OutPutStreamWriter, InpPutStreamReader) 문자들
 * 내장 메모리 파일 입출력 처리 3 (BufferedWritter, BufferedReader)

##### practice03_rawfolder
 raw 폴더를 이용한 파일 열기
 * InputStreamReader 를 이용한 파일 열기 <- 속도가 조금 느림
 * BufferedReader를 이용한 파일 읽기 <- 속독 훨씬 빠름

##### practice04_permissions
 AndroidManifest.xml 파일을 이용한 권한 설정
 권한을 요펑후에 허용된 권한 목록과 거부된 권한 목록을 TextView에서 보여줌

##### practice05_sdcard
 SDCard에 접근하기
 권한 체그하기 checkPermissions();
 * 외부 저장소 앱별 비공개 디렉토리에 파일 작성
 * 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기 - 바이트 단위 읽기
 * 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기 - 문자 단위 읽기

##### practice08__practice8-1
 DatePicker와 파일 입출력을 이용한 일기장 만들기
 
### Chap_10
##### ex01_practive10-1
 Intent를 이용한 Activity 전환
 
##### ex02_practive10-2
 Intent를 이용한 Activity 전환 + intent.putExtra로 전달 
  + intent.getIntent() 로 인텐트 객체 받기 + intent.get자료형Extra()로 받기

##### ex03_diy10-2
 Intent를 이용한 Activity 전환 + intent.putExtra로 전달 
  + intent.getIntent() 로 인텐트 객체 받기 + intent.get자료형Extra()로 받기

##### ex04_example10-16
 startActivivyForResult를 통한 Activivy전환
 setResult();
 onActivivyResult() 함수 호출

##### ex05_diy10-3
 intent로 계산하기

##### ex06_example10-20
 암시적 intent

##### practice10_1
 라디오 버튼이 선택한 Acitivity로 intent를 통해 전환
 
### chap_11
##### ex01_listView
 ListView사용하기 ( 리스트에 데이터를 추가, 보이기)

##### ex02_listView
 프로세스 바가 포함된 listView 사용
 row.xml을 디자인해서 사용

##### ex03_listView_addmodifydelete
 리스트 아이템 추가,수정,삭제

##### ex04_listView_simpleadapter
 리스트 아이템을 클릭했을때 내용을 TextView에 보이기

##### ex05_listview_simpleadapter
 HashMap을 이용하여 아이템 하나에 3가지 컨텍스트 설정

##### ex06_listView_customadapter
 직접 어댑터를 만들어서 데이터 수정 추가 기능

##### ex07_listView_customadapter
 직접 어댑터를 만들어서 데이터 수정 가능
 아이템당 하나의 객체를 만듬
 ## 커스텀 listView를 만들고 싶다면 여기로!

##### ex08_spinner
 리스트 스피너 설정
 setDropDownViewResource(android.R.layout.simple_spinner_item);
