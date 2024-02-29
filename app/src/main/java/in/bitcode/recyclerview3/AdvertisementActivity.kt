package `in`.bitcode.recyclerview3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdvertisementActivity : AppCompatActivity() {

    private lateinit var txtAdv : TextView
    private lateinit var advertisement: Advertisement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advertisement_activity)
        txtAdv = findViewById(R.id.txtAdv)

        advertisement = intent.getSerializableExtra("adv") as Advertisement
        txtAdv.text = advertisement.title
    }
}