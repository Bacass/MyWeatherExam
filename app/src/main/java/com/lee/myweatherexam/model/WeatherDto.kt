package com.lee.myweatherexam.model

class WeatherDto {

    var list: ArrayList<WeatherData> = ArrayList()
    var parm: WeatherData? = null
    var ArpltnInforInqireSvcVo: WeatherData? = null
    var totalCount: Int? = null
}

data class WeatherData(
    // Grade 1:좋음, 2:보통, 3:나쁨, 4:매우나쁨
    val _returnType: String,
    val coGrade: String,        // 일산화탄소 지수
    val coValue: String,        // 일산화탄소 농도
    val dataTerm: String,
    val dataTime: String,       // 측정일
    val khaiGrade: String,      // 통합대기환경지수
    val khaiValue: String,      // 통합대기환경수치
    val mangName: String,       // 측정망 정보 (국가배경, 교외대기, 도시대기, 도로변대기)
    val no2Grade: String,       // 이산화질소 지수
    val no2Value: String,       // 이산화질소 농도
    val numOfRows: String,      // 한 페이지 결과 수
    val o3Grade: String,        // 오존 지수
    val o3Value: String,        // 오존 농도
    val pageNo: String,         // 페이지 번호
    val pm10Grade: String,      // 미세먼지(PM10) 24시간 등급
    val pm10Grade1h: String,    // 미세먼지(PM10) 1시간 등급
    val pm10Value: String,      // 미세먼지(PM10) 농도 (단위 : ㎍/㎥)
    val pm10Value24: String,    // 미세먼지(PM10) 24시간예측이동농도 (단위 : ㎍/㎥)
    val pm25Grade: String,      // 미세먼지(PM2.5) 24시간 등급
    val pm25Grade1h: String,    // 미세먼지(PM2.5) 1시간 등급
    val pm25Value: String,      // 미세먼지(PM2.5) 농도 미세먼지(PM2.5)  농도 (단위 : ㎍/㎥)
    val pm25Value24: String,    // 미세먼지(PM2.5) 24시간예측이동농도 (단위 : ㎍/㎥)
    val resultCode: String,     // 결과코드
    val resultMsg: String,      // 결과메세지
    var rnum: Int,
    val serviceKey: String,
    val sidoName: String,
    val so2Grade: String,       // 아황산가스 지수
    val so2Value: String,       // 아황산가스 농도
    val stationCode: String,
    val stationName: String,
    val totalCount: String,     // 전체 결과 수
    val ver: String
)