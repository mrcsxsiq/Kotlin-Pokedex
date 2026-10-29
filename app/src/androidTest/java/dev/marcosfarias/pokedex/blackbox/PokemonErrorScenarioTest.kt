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
class PokemonErrorScenarioTest {

    @Test
    fun appRemainsRunningAfterInvalidSearchInput() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val device = UiDevice.getInstance(instrumentation)
        val packageName = instrumentation.targetContext.packageName

        val launchIntent = instrumentation.targetContext
            .packageManager
            .getLaunchIntentForPackage(packageName)

        requireNotNull(launchIntent) {
            "Не найден экран запуска приложения"
        }

        launchIntent.addFlags(
            android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        instrumentation.targetContext.startActivity(launchIntent)

        assertTrue(
            "Приложение не запустилось",
            device.wait(Until.hasObject(By.pkg(packageName)), 5_000)
        )

        // Если на стартовом экране есть поле ввода, проверяем
        // реакцию на заведомо несуществующее имя.
        val input = device.findObject(By.clazz("android.widget.EditText"))

        if (input != null) {
            input.click()
            input.setText("pokemon_does_not_exist_12345")
            device.pressEnter()
            device.waitForIdle()
        }

        assertTrue(
            "Приложение закрылось после некорректного ввода",
            device.hasObject(By.pkg(packageName))
        )
    }
}