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

package apps.r2b.swapi.presentation.planetDetail

import android.os.Bundle
import apps.r2b.swapi.R
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.presentation.common.BaseActivity
import apps.r2b.swapi.presentation.common.ScreenSharedDataProvider
import kotlinx.android.synthetic.main.act_planet_detail.*


class PlanetDetailActivity : BaseActivity(), ScreenSharedDataProvider<Planet> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_planet_detail)

        val planet = getData()

        tvName.text = planet.name
        tvPopulation.text = String.format(getString(R.string.population_args, planet.population))
    }

    override fun getData(): Planet {
        val screen: PlanetDetailScreen = screenResolver.getScreen(this)
        return screen.data
    }

}