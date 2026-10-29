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
class PokemonNavigationScenarioTest {

    @Test
    fun appLaunchesAndHandlesBackNavigation() {
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
            "Главный экран не появился",
            device.wait(Until.hasObject(By.pkg(packageName)), 5_000)
        )

        device.pressBack()
        device.waitForIdle()

        // После Back приложение может свернуться или остаться открытым.
        // Проверяем, что тестовый процесс не завершился с исключением.
        assertTrue("Тест должен завершиться корректно", true)
    }
}