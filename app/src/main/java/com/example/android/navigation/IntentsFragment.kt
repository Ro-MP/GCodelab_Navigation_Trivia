package com.example.android.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import java.util.*


class IntentsFragment : Fragment() {

    //lateinit var binding: FragmentIntentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_intents, container, false)

        val buttonViewMap: Button = inflater.findViewById(R.id.btn_map)
        val buttonCall: Button = inflater.findViewById(R.id.btn_phone_call)
        val buttonCalendarEvent: Button = inflater.findViewById(R.id.btn_calendar)

        buttonViewMap.setOnClickListener{
            viewMap()
        }
        buttonCall.setOnClickListener {
            madePhoneCall()
        }
        buttonCalendarEvent.setOnClickListener {
            madeCalendarEvent()
        }

        return inflater
    }


    private fun getMapIntent(): Intent {
        val mapIntent: Intent = Uri.parse(
            "geo:20.216514271748242, -98.58866997070787 ?q=Barbacoa Garcías" ,


        ).let { location ->
            // Or map point based on latitude/longitude
            //val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
            Intent(Intent.ACTION_VIEW, location)
        }
        return mapIntent
    }

    private fun viewMap(): Boolean{
        return try {
            startActivity(getMapIntent())
            true
        } catch (e: ActivityNotFoundException){
            launchToast("No app receive Map Intent")
            false
        }
    }

    private fun getCallIntent(): Intent {
        val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+525521758219"))
        return callIntent
    }

    private fun madePhoneCall(): Boolean{
        return try {
            startActivity(getCallIntent())
            true
        } catch (e: ActivityNotFoundException){
            launchToast("No app receive Phone Call Intent")
            false
        }
    }

    private fun getCalendarIntent(): Intent {
        val calendarIntent: Intent = Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
            val beginTime: Calendar = Calendar.getInstance().apply {
                set(2023, 0, 13, 15, 30)
            }
            val endTime = Calendar.getInstance().apply {
                set(2021, 0, 15, 10, 0)
            }
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
            putExtra(CalendarContract.Events.TITLE, "FGM")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo")
        }
        return calendarIntent
    }

    private fun madeCalendarEvent(): Boolean{
        return try {
            startActivity(getCalendarIntent())
            true
        } catch (e: ActivityNotFoundException){
            launchToast("No app receive Calendar Intent")
            false
        }
    }

    private fun launchToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}