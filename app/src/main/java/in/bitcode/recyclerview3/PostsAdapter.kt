package `in`.bitcode.recyclerview3

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class PostsAdapter(
    private val posts : ArrayList<Post>,
    private val advertisements: ArrayList<Advertisement>
) : RecyclerView.Adapter<ViewHolder>(){

    interface OnPostClickListener {
        fun onPostClick(position : Int, post : Post)
    }
    var onPostClickListener : OnPostClickListener? = null


    val VIEW_TYPE_POST = 1
    val VIEW_TYPE_ADV = 2

    override fun getItemViewType(position: Int): Int {
        return  if(position  % 3 == 2) {
            VIEW_TYPE_ADV
        }
        else {
            VIEW_TYPE_POST
        }
    }

    inner class PostViewHolder(
        val view : View
    ) : RecyclerView.ViewHolder(view) {
        val txtUsername : TextView
        val imgPost : ImageView
        val txtPostTitle : TextView

        init {
            txtUsername = view.findViewById(R.id.txtUsername)
            txtPostTitle = view.findViewById(R.id.txtPostTitle)
            imgPost = view.findViewById(R.id.imgPost)

            itemView.setOnClickListener {

                if(onPostClickListener != null) {
                    onPostClickListener!!.onPostClick(
                        adapterPosition - adapterPosition / 3,
                        posts[adapterPosition - adapterPosition / 3]
                    )
                }

                //fixed actions are not good
                /*val intent = Intent(it.context, PostDetailsActivity::class.java)
                intent.putExtra("post", posts[adapterPosition - adapterPosition / 3])
                it.context.startActivity(intent)*/
            }
        }
    }

    interface OnAdvClickListener {
        fun onAdvClick(position: Int, advertisement: Advertisement)
    }
    var onAdvClickListener : OnAdvClickListener? = null

    inner class AdvViewHolder(
        val view : View
    ) : RecyclerView.ViewHolder(view) {
        val txtAdv : TextView
        init {
            txtAdv = view.findViewById(R.id.txtAdv)

            txtAdv.setOnClickListener {
                onAdvClickListener?.onAdvClick(
                    adapterPosition/2 -1,
                    advertisements[adapterPosition/2 -1]
                )

                //fixed actions are not good
                /*val intent = Intent(it.context, AdvertisementActivity::class.java)
                intent.putExtra("adv", advertisements[adapterPosition/2 - 1])
                it.context.startActivity(intent)*/
            }
        }
    }

    override fun getItemCount() = posts.size + advertisements.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType == VIEW_TYPE_POST) {
            return PostViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_view, null)
            )
        }

        //create adv view holder and return
        return AdvViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adv_view, null)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is PostViewHolder) {
            val post = posts[position - position / 3]
            holder.txtUsername.text = post.username
            holder.txtPostTitle.text = post.title
            holder.imgPost.setImageResource(post.imageId)
        }
        if(holder is AdvViewHolder){
            val adv = advertisements[position/2 -1];
            holder.txtAdv.text = adv.title
        }
    }

}