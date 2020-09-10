package klinka.com.newsnow.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: List<Article>
): Serializable
