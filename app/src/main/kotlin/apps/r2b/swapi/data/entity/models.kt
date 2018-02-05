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

package apps.r2b.swapi.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URI
import java.util.*

/**
 * Planet.
 *
 * The climate of this planet. Comma-seperated if diverse. (Required)
 * The hypermedia URL of this resource. (Required)
 * The average population of sentient beings inhabiting this planet. (Required)
 * An array of Film URL Resources that this planet has appeared in. (Required)
 * A number denoting the gravity of this planet. Where 1 is normal. (Required)
 * The percentage of the planet surface that is naturally occuring water or bodies of water. (Required)
 * The name of this planet. (Required)
 * The ISO 8601 date format of the time that this resource was created. (Required)
 * The number of standard days it takes for this planet to complete a single orbit of its local star. (Required)
 * The terrain of this planet. Comma-seperated if diverse. (Required)
 * The ISO 8601 date format of the time that this resource was edited. (Required)
 * The diameter of this planet in kilometers. (Required)
 * The number of standard hours it takes for this planet to complete a single rotation on its axis. (Required)
 * An array of People URL Resources that live on this planet. (Required)
 */
data class Planet(@SerializedName("climate") @Expose var climate: String,
                  @SerializedName("url") @Expose var url: URI,
                  @SerializedName("population") @Expose var population: String,
                  @SerializedName("films") @Expose var films: List<URI>,
                  @SerializedName("gravity") @Expose var gravity: String,
                  @SerializedName("surface_water") @Expose var surfaceWater: String,
                  @SerializedName("name") @Expose var name: String,
                  @SerializedName("created") @Expose var created: Date,
                  @SerializedName("orbital_period") @Expose var orbitalPeriod: String,
                  @SerializedName("terrain") @Expose var terrain: String,
                  @SerializedName("edited") @Expose var edited: Date,
                  @SerializedName("diameter") @Expose var diameter: String,
                  @SerializedName("rotation_period") @Expose var rotationPeriod: String,
                  @SerializedName("residents") @Expose var residents: List<URI>) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readSerializable() as URI,
            source.readString(),
            ArrayList<URI>().apply { source.readList(this, URI::class.java.classLoader) },
            source.readString(),
            source.readString(),
            source.readString(),
            source.readSerializable() as Date,
            source.readString(),
            source.readString(),
            source.readSerializable() as Date,
            source.readString(),
            source.readString(),
            ArrayList<URI>().apply { source.readList(this, URI::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(climate)
        writeSerializable(url)
        writeString(population)
        writeList(films)
        writeString(gravity)
        writeString(surfaceWater)
        writeString(name)
        writeSerializable(created)
        writeString(orbitalPeriod)
        writeString(terrain)
        writeSerializable(edited)
        writeString(diameter)
        writeString(rotationPeriod)
        writeList(residents)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Planet> = object : Parcelable.Creator<Planet> {
            override fun createFromParcel(source: Parcel): Planet = Planet(source)
            override fun newArray(size: Int): Array<Planet?> = arrayOfNulls(size)
        }
    }

}
