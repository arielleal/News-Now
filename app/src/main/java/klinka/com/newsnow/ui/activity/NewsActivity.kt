package klinka.com.newsnow.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import klinka.com.newsnow.CHAVE
import klinka.com.notciasagora.R
import klinka.com.newsnow.model.Source
import klinka.com.newsnow.model.enums.Category
import klinka.com.newsnow.model.enums.Country
import klinka.com.newsnow.ui.adapter.NewsAdapter
import klinka.com.newsnow.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity: AppCompatActivity(), NewsAdapter.NewsAdapterListening {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listSource: List<Source>
    private lateinit var newsAdapter: NewsAdapter
    private var itemSelectedCategory: String? = null
    private var itemSelectedCountry: String? = null
    private val newsViewModel: NewsViewModel = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        newsViewModel.loadSource(itemSelectedCountry,itemSelectedCategory)
        configureAdapter()
        configureSpiner()

        newsViewModel.sources.observe(this, Observer {
            update(newsViewModel.sources.value!!)
        })

        newsViewModel.errorMessage.observe(this, Observer {
            showErrorToastNews(newsViewModel.errorMessage.value!!)
        })
    }

    fun update(source: List<Source>) {
        listSource = source
        newsAdapter.refeshList(listSource)
    }

    private fun configureSpiner(){
        val listCategory= Category.values().toMutableList()
        val listCountry = Country.values().toMutableList()

        val adapterCategory = ArrayAdapter(this, android.R.layout.simple_spinner_item,listCategory)
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner_category_id.adapter = adapterCategory

        val adapterCountry = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCountry)
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner_country_id.adapter = adapterCountry

        spiner_category_id.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0){
                    itemSelectedCategory = null
                    newsViewModel.loadSource(itemSelectedCountry,itemSelectedCategory)
                }else{
                    itemSelectedCategory = listCategory[position].toString()
                    newsViewModel.loadSource(itemSelectedCountry,itemSelectedCategory)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spiner_country_id.onItemSelectedListener =object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0){
                itemSelectedCountry = null
                newsViewModel.loadSource(itemSelectedCountry,itemSelectedCategory)
                }else{
                    itemSelectedCountry = listCountry[position].toString()
                    newsViewModel.loadSource(itemSelectedCountry,itemSelectedCategory)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun configureAdapter() {
        recyclerView = findViewById(R.id.news_recyclerView_id)
        newsAdapter = NewsAdapter(emptyList(), this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter
    }

    override fun onClick(source: Source) {
        val intent = Intent(this, ListNewsActivity::class.java)
        intent.putExtra(CHAVE, source)
        startActivity(intent)
    }

    fun showErrorToastNews(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    }
}