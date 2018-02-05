/*
 * Copyright (c) 2018.
 *
 * No permission is granted to any person to copy, modify, disseminate, publish,
 * distribute, sublicense and / or sell this software and associated documentation
 * files (the "Software").
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package apps.r2b.swapi


import android.content.Context
import apps.r2b.swapi.data.Repository
import apps.r2b.swapi.data.RepositoryImpl
import apps.r2b.swapi.data.persistence.PersistenceManager
import apps.r2b.swapi.data.persistence.PersistenceManagerImpl
import apps.r2b.swapi.data.rest.Planets
import apps.r2b.swapi.data.rest.PlanetsDeserializer
import apps.r2b.swapi.data.rest.Swapi
import apps.r2b.swapi.data.rest.SwapiAdapter
import apps.r2b.swapi.presentation.common.ScreenEventListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient


fun _repository(appContext: Context): Repository {
    return RepositoryImpl(_swapi(), _persistenceManager(appContext))
}

fun _subscribeOn(): Scheduler {
    return AndroidSchedulers.mainThread()
}

fun _observeOn(): Scheduler {
    return AndroidSchedulers.mainThread()
}

fun _persistenceManager(appContext: Context): PersistenceManager {
    return PersistenceManagerImpl(appContext, _jsonParser())
}

fun _swapi(): Swapi {
    return SwapiAdapter(_httpClient(), _jsonParser()).api()
}

fun _httpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun _jsonParser(): Gson {
    return GsonBuilder()
            .registerTypeAdapter(Planets::class.java, PlanetsDeserializer())
            .create()
}

fun _screenEventListener(): ScreenEventListener {
    return SwapiScreenEventListener()
}

fun _type(): String {
    return "instrumented test"
}
