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

package apps.r2b.swapi

import android.content.Context
import apps.r2b.swapi.data.Repository
import apps.r2b.swapi.data.persistence.PersistenceManager
import apps.r2b.swapi.presentation.common.ScreenEventListener
import io.reactivex.Scheduler

// TODO Improve with provider delegate

class Injector {

    companion object {

        fun repository(appContext: Context): Repository {
            return _repository(appContext)
        }

        fun subscribeOn(): Scheduler {
            return _subscribeOn()
        }

        fun observeOn(): Scheduler {
            return _observeOn()
        }

        fun persistenceManager(appContext: Context): PersistenceManager {
            return _persistenceManager(appContext)
        }

        fun screenEventListener(): ScreenEventListener {
            return _screenEventListener()
        }

        fun type(): String {
            return _type()
        }

    }

}