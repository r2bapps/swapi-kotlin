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

package apps.r2b.swapi.presentation.planetList

import apps.r2b.swapi.data.Repository
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.presentation.planetDetail.PlanetDetailScreen
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import me.aartikov.alligator.AndroidNavigator


class PlanetListPresenter(private val planetListView: PlanetListView,
                          private val repository: Repository,
                          private val subscriber: Scheduler,
                          private val observer: Scheduler,
                          private val navigator: AndroidNavigator) {

    fun init() {
        repository.getPlanets()
            .subscribeOn(subscriber)
            .observeOn(observer)
            .subscribe(object: SingleObserver<List<Planet>>{
                override fun onSuccess(list: List<Planet>) {
                    planetListView.update(list)
                }

                override fun onSubscribe(d: Disposable) {
                    // ignore
                }

                override fun onError(e: Throwable) {
                    planetListView.error("An error has occurred:\n\n" + e.localizedMessage)
                }
            })
    }

    fun onSelectedItem(planet: Planet) {
        navigator.goForward(PlanetDetailScreen(planet))
    }

}
