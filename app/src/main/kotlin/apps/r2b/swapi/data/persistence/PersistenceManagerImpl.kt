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

import android.content.Context
import android.support.annotation.VisibleForTesting
import apps.r2b.swapi.OpenForTesting
import apps.r2b.swapi.data.entity.Planet
import apps.r2b.swapi.toFile
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception

open class PersistenceManagerImpl(appContext: Context,
                                  private val jsonParser: Gson) : AnkoLogger, PersistenceManager {

    private val cachedFile: File = File(appContext.cacheDir, "swapi.json")

    @VisibleForTesting
    fun getCachedFile(): File {
        return cachedFile
    }

    override fun getCachedData(): List<Planet> {
        var list: List<Planet> = emptyList()

        try {
            val input: InputStream = FileInputStream(cachedFile)
            val planetType = object : TypeToken<List<Planet>>(){}.type
            list = jsonParser.fromJson(input.bufferedReader(Charsets.UTF_8), planetType)
        } catch(ex: Exception) {
            when(ex) {
                is JsonSyntaxException -> {
                    error { "Some error has occurred parsing file ${cachedFile.absolutePath}" }
                }
                else -> {
                    error { "${cachedFile.absolutePath} file was deleted or is corrupted" }
                }
            }
        } finally {
            return list
        }

    }

    override fun setCachedData(data: List<Planet>) {
        (jsonParser.toJson(data).byteInputStream(Charsets.UTF_8) as InputStream).toFile(cachedFile.absolutePath)
    }

    @OpenForTesting
    override fun isCached(): Boolean {
        val file = File(cachedFile.absolutePath)
        return file.isFile && file.canRead()
    }

}
