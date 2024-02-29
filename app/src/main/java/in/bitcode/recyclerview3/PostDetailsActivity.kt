package `in`.bitcode.recyclerview3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var post : Post
    private lateinit var txtPostTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_details_activity)
        txtPostTitle = findViewById(R.id.txtPostTitle)

        post = intent.getSerializableExtra("post") as Post
        txtPostTitle.text = post.title

    }
}