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

import android.os.Parcel
import android.os.Parcelable
import apps.r2b.swapi.data.entity.Planet
import me.aartikov.alligator.Screen


data class PlanetDetailScreen(val data: Planet) : Screen, Parcelable {

    constructor(parcel: Parcel) : this(parcel.readParcelable(Planet::class.java.classLoader) as Planet)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents(): Int  = 0

    companion object CREATOR : Parcelable.Creator<PlanetDetailScreen> {
        override fun createFromParcel(parcel: Parcel): PlanetDetailScreen {
            return PlanetDetailScreen(parcel)
        }
        override fun newArray(size: Int): Array<PlanetDetailScreen?> {
            return arrayOfNulls(size)
        }
    }

}