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

import android.support.v7.app.AppCompatActivity
import android.view.View
import apps.r2b.swapi.Injector
import apps.r2b.swapi.R
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.Screen
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.ScreenResult
import me.aartikov.alligator.listeners.ScreenResultListener
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher
import org.jetbrains.anko.findOptional

abstract class BaseActivity : AppCompatActivity(), ScreenResultListener {

    private val screenEventListener: ScreenEventListener by lazy { Injector.screenEventListener() }
    protected val screenResolver: ScreenResolver by lazy {
        (applicationContext as HasNavigator).getNavigator().screenResolver
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        bindNavigator()
    }

    override fun onPause() {
        unbindNavigator()
        super.onPause()
    }

    override fun onScreenResult(screenClass: Class<out Screen>, result: ScreenResult?) {
        // override if needed
    }

    open fun bindNavigator() {
        if (application is HasNavigator) {
            val navigationContextBuilder = NavigationContext.Builder(this)
                    .screenResultListener(this)
                    .transitionListener(screenEventListener)
                    .screenSwitchingListener(screenEventListener)
                    .dialogShowingListener(screenEventListener)
            if (findOptional<View>(R.id.fragment_container) != null) {
                val screenSwitcher = FragmentScreenSwitcher(supportFragmentManager, R.id.fragment_container)
                navigationContextBuilder.containerId(R.id.fragment_container).screenSwitcher(screenSwitcher)
            }
            val navigationContext = navigationContextBuilder.build()
            (application as HasNavigator).getNavigator().bind(navigationContext)
        }
    }

    open fun unbindNavigator() {
        if (application is HasNavigator) {
            (application as HasNavigator).getNavigator().unbind()
        }
    }

}
