package `in`.bitcode.recyclerview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerPosts: RecyclerView
    private val posts = ArrayList<Post>()
    private val advertisements = ArrayList<Advertisement>()

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initData()
        initAdapter()
    }

    private fun initData() {
        for (i in 0..20) {
            posts.add(
                Post(
                    "bitcode-$i",
                    "Dummy post title by user $i",
                    R.mipmap.ic_launcher
                )
            )
        }

        for (i in 0..6) {
            advertisements.add(
                Advertisement(
                    "Join the Android classes @bitcode ($i)",
                    "https://bitcode.in"
                )
            )
        }
    }

    private fun initAdapter() {
        postsAdapter = PostsAdapter(posts, advertisements)
        recyclerPosts.adapter = postsAdapter
    }

    private fun initViews() {
        recyclerPosts = findViewById(R.id.recyclerPosts)
        recyclerPosts.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}