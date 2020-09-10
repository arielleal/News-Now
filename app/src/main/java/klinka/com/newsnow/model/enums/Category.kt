package klinka.com.newsnow.model.enums

import klinka.com.notciasagora.R

enum class Category(private val responseId: Int): BaseEnum {
    CATEGORIES(R.string.ALL_CATEGORIES), BUSINESS(R.string.BUSINESS), ENTERTAINMENT(R.string.ENTERTAINMENT), GENERAL(R.string.GENERAL),
    HEALTH(R.string.HEALTH), SCIENCE(R.string.SCIENCE), SPORTS(R.string.SPORTS), TECHNOLOGY(R.string.TECHNOLOGY);

    override fun getResponse(): Int {
        return responseId
    }
}
