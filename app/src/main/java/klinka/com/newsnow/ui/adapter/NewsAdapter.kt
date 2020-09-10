package klinka.com.newsnow.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import klinka.com.newsnow.ICON_LOCATOR_URL
import klinka.com.notciasagora.R
import klinka.com.newsnow.model.Source
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private var dataList: List<Source>,
                  private val context: Context,
                  private val listener: NewsAdapterListening
):
    RecyclerView.Adapter<NewsAdapter.SourceViewHolder>() {

    class SourceViewHolder(val itemLayoutView: View): RecyclerView.ViewHolder(itemLayoutView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false)
        return SourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        var sourceModel: Source = dataList[position]

        holder.itemLayoutView.setOnClickListener{ listener.onClick(sourceModel) }
        holder.itemLayoutView.item_news_title_text.text = sourceModel.name
        holder.itemLayoutView.item_news_description_text.text = sourceModel.description
        holder.itemLayoutView.item_news_url_text.text = sourceModel.url
        Picasso.get()
            .load(ICON_LOCATOR_URL.format(sourceModel.url))
            .fit()
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(holder.itemLayoutView.item_news_image)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun refeshList(listSource: List<Source>){
        dataList = listSource
        notifyDataSetChanged()
    }

    interface NewsAdapterListening {
        fun onClick(source: Source)
    }
}
