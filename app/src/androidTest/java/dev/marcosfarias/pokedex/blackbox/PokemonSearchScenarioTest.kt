package dev.marcosfarias.pokedex.blackbox

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonSearchScenarioTest {

    @Test
    fun userCanEnterPokemonSearchQuery() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val device = UiDevice.getInstance(instrumentation)
        val packageName = instrumentation.targetContext.packageName

        val intent = instrumentation.targetContext
            .packageManager
            .getLaunchIntentForPackage(packageName)

        requireNotNull(intent) { "Не найден launcher Activity" }

        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
        instrumentation.targetContext.startActivity(intent)

        assertTrue(
            "Приложение не запустилось",
            device.wait(Until.hasObject(By.pkg(packageName)), 5_000)
        )

        // В некоторых версиях приложения поиск находится
        // на отдельном экране Pokédex.
        val pokedexButton = device.findObject(By.textContains("Pokédex"))
            ?: device.findObject(By.textContains("Pokedex"))

        if (pokedexButton != null) {
            pokedexButton.click()
            device.waitForIdle()
        }

        val searchInput = device.findObject(By.clazz("android.widget.EditText"))

        assertTrue(
            "Не найдено поле поиска. Проверьте экран и локаторы приложения.",
            searchInput != null
        )

        searchInput!!.click()
        searchInput.setText("pikachu")
        device.waitForIdle()

        assertTrue(
            "Приложение перестало отображаться после ввода запроса",
            device.hasObject(By.pkg(packageName))
        )
    }
}