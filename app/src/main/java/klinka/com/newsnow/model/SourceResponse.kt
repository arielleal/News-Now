package klinka.com.newsnow.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourceResponse (
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
): Serializable