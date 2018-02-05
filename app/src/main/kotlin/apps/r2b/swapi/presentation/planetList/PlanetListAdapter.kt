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

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import apps.r2b.swapi.R
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.inflate
import apps.r2b.swapi.presentation.common.ListAdapter
import apps.r2b.swapi.presentation.common.OnSelectedItemListener
import kotlinx.android.synthetic.main.lay_planet_item.view.*

class PlanetListAdapter(info: MutableList<Planet>,
                        @LayoutRes override val layoutRes: Int,
                        override val listener: OnSelectedItemListener<Planet>) : ListAdapter<Planet>(info, layoutRes, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlanetHolder(parent.inflate(layoutRes), listener)

    class PlanetHolder(itemView: View,
                       override val listener: OnSelectedItemListener<Planet>) : ListAdapter.VH<Planet>(itemView, listener) {
        override fun bind(itemData: Planet) {
            itemView.tvName.text = itemData.name
            itemView.setOnClickListener({ listener.itemSelected(itemData) })
            // TODO extension to get color depending on api
            if (adapterPosition % 2 == 0) {
                itemView.setBackgroundColor(itemView.context.resources.getColor(android.R.color.darker_gray))
            } else {
                itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.colorPrimary))
            }
        }
    }

}
