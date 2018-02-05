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

package apps.r2b.swapi.data

import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.data.persistence.PersistenceManager
import apps.r2b.swapi.data.rest.Swapi
import io.reactivex.Single


// TODO Replace Rx with native

class RepositoryImpl(private val api: Swapi,
                     private val persistenceManager: PersistenceManager) : Repository {

    override fun getPlanets(): Single<List<Planet>> {

        if (persistenceManager.isCached() ) {
            return Single.just(persistenceManager.getCachedData())
        } else {
            return api.getPlanets()
                    .doOnSuccess {
                        planetsList -> persistenceManager.setCachedData(planetsList)
                    }
                    .map {
                        planetsList -> planetsList
                    }
        }

    }

}


