package app.kitabcha.source

import app.kitabcha.source.helper.NetworkHelper
import app.kitabcha.source.model.Page
import okhttp3.Headers
import okhttp3.OkHttpClient

abstract class HttpSource : Source {
    abstract val domain: String

    val client: OkHttpClient = NetworkHelper.client

    protected open fun headersBuilder() =
        Headers.Builder()
            .set(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.3",
            )
            .set("Referer", "https://$domain/")
            .set("Origin", "https://$domain")

    val headers: Headers by lazy {
        headersBuilder().build()
    }

    override suspend fun getImageUrl(page: Page): String {
        return page.url
    }
}
