package klinka.com.newsnow.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import klinka.com.newsnow.CHAVE
import klinka.com.newsnow.ICON_LOCATOR_URL
import klinka.com.notciasagora.R
import klinka.com.newsnow.model.Article
import klinka.com.newsnow.model.Source
import klinka.com.newsnow.ui.adapter.ListNewsAdapter
import klinka.com.newsnow.ui.viewmodel.ListNewsViewModel
import kotlinx.android.synthetic.main.activity_list_news.*
import java.net.URI

class ListNewsActivity() : AppCompatActivity(), ListNewsAdapter.ListNewsAdapterListening {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ListNewsAdapter
    private lateinit var listArticles: List<Article>
    private lateinit var url: String
    private var listViewModel: ListNewsViewModel = ListNewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)
        configureAdapter()

        val sourceItem = intent.getSerializableExtra(CHAVE) as? Source
        list_news_name_text.text = sourceItem?.name
        url = sourceItem?.url.toString()

        Picasso.get()
            .load(ICON_LOCATOR_URL.format(url))
            .fit()
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(list_news_image_imageView)

        preparingDataToLoadEverything(url)

        listViewModel.category.observe(this, Observer {
            fillArticles(listViewModel.category.value!!)
        })

        listViewModel.errorMenssage.observe(this, Observer {
            showErrorToastListNews(listViewModel.errorMenssage.value!!)
        })
    }

    private fun preparingDataToLoadEverything(url: String) {
        val uri = URI(url)
        var domain = uri.host
        domain = domain.replace("www.", "")
        listViewModel.loadEverything(domain, 30)
    }

    fun fillArticles(articles: List<Article>){
        listArticles = articles
        listAdapter.refreshList(listArticles)
    }

    private fun configureAdapter() {
        recyclerView = findViewById((R.id.list_news_recyclerView))
        listAdapter = ListNewsAdapter(emptyList(),this,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    override fun onClick(article: Article) {
        val intent = Intent(this, DetailsNewsActivity::class.java)
        intent.putExtra(CHAVE, article)
        startActivity(intent)
    }

    fun showErrorToastListNews(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    }
}
