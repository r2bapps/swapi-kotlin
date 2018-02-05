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

import apps.r2b.swapi.data.persistence.PersistenceManager
import apps.r2b.swapi.data.rest.Swapi
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepositoryUnitTest {

    private lateinit var persistenceManager: PersistenceManager
    private lateinit var api: Swapi
    private lateinit var repository: Repository

    @Before
    fun setup() {
        persistenceManager = mock()
        api = mock()
        repository = RepositoryImpl(api, persistenceManager)
    }

    @Test
    fun testGetPlanetsCached() {
        whenever(persistenceManager.isCached()).thenReturn(true)
        repository.getPlanets()

        verify(persistenceManager).getCachedData()
    }

    @Test
    fun testGetPlanetsNotCached() {
        whenever(persistenceManager.isCached()).thenReturn(false)
        whenever(api.getPlanets()).thenReturn(Single.just(any()))
        repository.getPlanets()

        verify(api).getPlanets()
    }

    @Test
    fun testOnGetPlanetsNotCachedWillCached() {
        whenever(persistenceManager.isCached()).thenReturn(false)
        whenever(api.getPlanets()).thenReturn(Single.just(any()))
        repository.getPlanets()

        verify(api).getPlanets()
        verify(persistenceManager).setCachedData(any())
    }

}
