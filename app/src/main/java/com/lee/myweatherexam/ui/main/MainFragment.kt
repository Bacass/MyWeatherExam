package com.lee.myweatherexam.ui.main


import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.lee.myweatherexam.R
import com.lee.myweatherexam.databinding.MainFragmentBinding
import com.lee.myweatherexam.model.WeatherData
import com.lee.myweatherexam.model.WeatherDto
import com.lee.myweatherexam.network.NetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var mBinding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding = MainFragmentBinding.bind(getView()!!)

    }

    override fun onResume() {
        super.onResume()

//        Thread(Runnable {
//            //Do dome Network Request
//            activity?.runOnUiThread(Runnable {
//                //Update UI
//                requestWeather()
//            })
//        }).start()

        var runnable = Runnable {
            requestWeather()
        }

        Thread(Runnable {
            //Do dome Network Request
            activity?.runOnUiThread(runnable)
        }).start()

    }

    fun requestWeather() {
        try {
            Log.e("Lee", "========== 00")

            //    &numOfRows=10
            //    &pageNo=1
            //    &stationName=광진구
            //    &dataTerm=DAILY
            //    &ver=1.3
            //    &_returnType=json
            //    &ServiceKey=657OeqBgOnfylmDONGQhlx9erLCC7VZ4O32T6eW7WjWsGPiw5eimj46CfpLRQVmj55cf4k3BIr1p5XnGnmAl0w==

            var callBack = NetRetrofit.instance.service.getMsrstnAcctoRltmMesureDnsty("1", "1", "광진구", "DAILY", "1.3", "json", context!!.getString(R.string.service_key))



            callBack.enqueue(object : Callback<WeatherDto> {
                override fun onFailure(call: Call<WeatherDto>, t: Throwable) {
                    Log.e("Lee", "failed : " + t.message)
                }

                override fun onResponse(call: Call<WeatherDto>, response: Response<WeatherDto>) {
                    var json = Gson().toJson(response.body())
                    Log.e("Lee", "body : $json")
                    var WeatherDto = Gson().fromJson(json, WeatherDto::class.java)


                    var resultString = StringBuilder()
                    var weatherData : WeatherData? = WeatherDto.list[0]


                    // grade = 1:좋음, 2:보통, 3:나쁨, 4:매우나쁨

                    resultString.append("미세먼지")
                    resultString.append("\n")
                    resultString.append("PM10 value : ${weatherData?.pm10Value}")
                    resultString.append("\n")
                    resultString.append("PM10 grade: ${weatherData?.pm10Grade}, " + gradeToKorean(weatherData?.pm10Grade))
                    resultString.append("\n")
                    resultString.append("\n")
                    resultString.append("초미세먼지")
                    resultString.append("\n")
                    resultString.append("PM2.5 value : ${weatherData?.pm25Value}")
                    resultString.append("\n")
                    resultString.append("PM2.5 grade : ${weatherData?.pm25Grade}, " + gradeToKorean(weatherData?.pm25Grade))
                    resultString.append("\n")

                    mBinding?.message?.text = resultString.toString()
                }
            })


            Log.e("Lee", "========== 33")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun gradeToKorean(grade: String?) : String {
        when(grade) {
            "1" -> {
                return "좋음"
            }
            "2" -> {
                return "보통"
            }
            "3" -> {
                return "나쁨"
            }
            "4" -> {
                return "매우나쁨"
            }
            else -> {
                return "보통"
            }
        }
    }


}
