package `in`.bitcode.recyclerview3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

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

    private inner class MyOnPostClickListener : PostsAdapter.OnPostClickListener {
        override fun onPostClick(position: Int, post: Post) {

            if(isLoggedIn()) {
                val intent = Intent(this@MainActivity, PostDetailsActivity::class.java)
                intent.putExtra("post", post)
                startActivity(intent)
                Toast.makeText(this@MainActivity, "Post: ${post.title}", Toast.LENGTH_LONG).show()
            }
            else {
                startActivity(
                    Intent(this@MainActivity, LoginActivity::class.java)
                )
            }
        }
    }

    private fun isLoggedIn() : Boolean{
        return Random().nextBoolean()
    }

    private inner class MyOnAdvClickListener : PostsAdapter.OnAdvClickListener {
        override fun onAdvClick(position: Int, advertisement: Advertisement) {
            Toast.makeText(this@MainActivity, "Adv: ${advertisement.title}", Toast.LENGTH_LONG).show()
        }
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

        postsAdapter.onPostClickListener = MyOnPostClickListener()
        postsAdapter.onAdvClickListener = MyOnAdvClickListener()
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