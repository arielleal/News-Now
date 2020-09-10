package klinka.com.newsnow.ui.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import klinka.com.newsnow.CHAVE
import klinka.com.notciasagora.R
import klinka.com.newsnow.model.Article
import kotlinx.android.synthetic.main.activity_details_news.*


class DetailsNewsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_news)

        val articleItem = intent.getSerializableExtra(CHAVE) as? Article
        articleItem?.let { configureView(it) }

        buttonListener(articleItem)
    }

    private fun buttonListener(articleItem: Article?) {
        detail_news_button.setOnClickListener(View.OnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem?.url))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this, "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun configureView(article: Article){
        details_news_title.text = article.title
        details_news_description.text = article.description
        details_news_author.text = article.author
        details_news_date.text = article.getPublishedDate()

        Picasso.get()
            .load(article.urlToImage)
            .fit()
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(details_news_imageView)

    }
}
