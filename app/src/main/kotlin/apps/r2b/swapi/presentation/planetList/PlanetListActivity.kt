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

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import apps.r2b.swapi.Injector
import apps.r2b.swapi.R
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.presentation.common.BaseActivity
import apps.r2b.swapi.presentation.common.HasNavigator
import apps.r2b.swapi.presentation.common.OnSelectedItemListener
import kotlinx.android.synthetic.main.act_list.*
import org.jetbrains.anko.toast


class PlanetListActivity : BaseActivity(), PlanetListView {

    private val planetsListPresenter: PlanetListPresenter by lazy {
        PlanetListPresenter(
            this,
            Injector.repository(this.applicationContext),
            Injector.subscribeOn(),
            Injector.observeOn(),
            (this.applicationContext as HasNavigator).getNavigator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_list)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = PlanetListAdapter(
                mutableListOf<Planet>(),
                R.layout.lay_planet_item,
                object : OnSelectedItemListener<Planet> {
                    override fun itemSelected(item: Planet) {
                        planetsListPresenter.onSelectedItem(item)
                    }
                }
        )
        planetsListPresenter.init()
    }

    override fun update(list: List<Planet>) {
        (rvList.adapter as PlanetListAdapter).update(list)
    }

    override fun error(message: String) {
        toast(message)
    }

}
