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

package apps.r2b.swapi.presentation.common

import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class ListAdapter<T>(data: MutableList<T>,
                              @LayoutRes protected open val layoutRes: Int,
                              protected open val listener: OnSelectedItemListener<T>) : RecyclerView.Adapter<ListAdapter.VH<T>>() {

    protected val info: MutableList<T> = data.toMutableList()

    override fun onBindViewHolder(holder: VH<T>, position: Int) = holder.bind(info[position])

    override fun getItemCount(): Int = info.size

    fun update(@NonNull newInfo: List<T>) {
        info.clear()
        info.addAll(newInfo)
        notifyDataSetChanged()
    }

    abstract class VH<T>(itemView: View, protected open val listener: OnSelectedItemListener<T>) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(itemData: T)
    }

}
