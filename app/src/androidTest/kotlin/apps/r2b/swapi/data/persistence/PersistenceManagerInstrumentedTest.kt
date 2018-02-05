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

package apps.r2b.swapi.data.persistence

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import apps.r2b.swapi.Injector
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.toFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class PersistenceManagerInstrumentedTest {

    private lateinit var manager: PersistenceManager
    private val FILE: String = "swapi_planets.json"
    private val ITEMS: Int = 10

    fun getCahedFile() = (manager as PersistenceManagerImpl).getCachedFile()

    @Before
    fun setup() {
        manager = Injector.persistenceManager(InstrumentationRegistry.getTargetContext())
        getCahedFile().delete()
    }

    @Test
    fun testEmptyListWhenGetCachedDataWithNoCachedFile() {
        assertTrue("Cached data must be empty", manager.getCachedData().isEmpty())
    }

    @Test
    fun testEmptyListWhenGetCachedDataWithCorruptedFile() {
        getCahedFile().printWriter(Charsets.UTF_8).print(true)

        assertTrue("Cached data must be empty", manager.getCachedData().isEmpty())
    }

    @Test
    fun testGetCachedData() {
        var input: InputStream = InstrumentationRegistry
                .getContext()
                .getResources()
                .getAssets()
                .open(FILE)
        input.toFile(getCahedFile().absolutePath)

        assertTrue("Cached data must have $ITEMS items", manager.getCachedData().size == ITEMS)
    }

    @Test
    fun testSetCachedData() {
        var input: InputStream = InstrumentationRegistry
                .getContext()
                .getResources()
                .getAssets()
                .open(FILE)
        var parser = Gson()
        val gnomeType = object : TypeToken<List<Planet>>(){}.type
        var list: List<Planet> = parser.fromJson(input.bufferedReader(Charsets.UTF_8), gnomeType)

        manager.setCachedData(list)

        assertTrue("Cached data must have $ITEMS items", manager.getCachedData().size == ITEMS)
    }

    @Test
    fun testIsNotCached() {
        assertFalse("File can't be cached", manager.isCached())
    }

    @Test
    fun testIsCached() {
        getCahedFile().printWriter(Charsets.UTF_8).print(true)

        assertTrue("File must be cached", manager.isCached())
    }

}
