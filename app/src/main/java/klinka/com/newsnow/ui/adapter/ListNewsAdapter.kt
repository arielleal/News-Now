package klinka.com.newsnow.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import klinka.com.notciasagora.R
import klinka.com.newsnow.model.Article

class ListNewsAdapter(private var datalist: List<Article>, private val context: Context, private val listener: ListNewsAdapterListening):
    RecyclerView.Adapter<ListNewsAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(var itemListLayout: View): RecyclerView.ViewHolder(itemListLayout){
        var listItemTitle: TextView = itemListLayout.findViewById(R.id.item_list_news_title_text)
        var listItemData: TextView = itemListLayout.findViewById(R.id.item_list_news_data_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(
                (R.layout.item_list_news),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var categoryList = datalist[position]

        holder.itemListLayout.setOnClickListener{listener.onClick(categoryList)}
        holder.listItemData.text = categoryList.getPublishedDate()
        holder.listItemTitle.text = categoryList.title
    }

    override fun getItemCount(): Int {
        return  datalist.size
    }

    fun refreshList(listArticles: List<Article>){
        datalist = listArticles
        notifyDataSetChanged()
    }

    interface ListNewsAdapterListening{
        fun onClick (article: Article)
    }
}