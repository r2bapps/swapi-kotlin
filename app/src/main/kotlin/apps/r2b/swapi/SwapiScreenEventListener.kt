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

import apps.r2b.swapi.presentation.common.ScreenEventListener
import me.aartikov.alligator.Screen
import me.aartikov.alligator.TransitionType
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


// TODO Replace Anko with event logger

open class SwapiScreenEventListener : ScreenEventListener, AnkoLogger {

    override fun onScreenTransition(transitionType: TransitionType?,
                                    screenClassFrom: Class<out Screen>?,
                                    screenClassTo: Class<out Screen>?,
                                    isActivity: Boolean) {
        info { "onScreenTransition" }
    }

    override fun onScreenSwitched(screenFrom: Screen?, screenTo: Screen?) {
        info { "onScreenSwitched" }
    }

    override fun onDialogShown(screenClass: Class<out Screen>?) {
        info { "onDialogShown" }
    }

}