package com.stslex

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.ApplicationConfigurationException
import io.ktor.server.config.HoconApplicationConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertTrue

@RunWith(Parameterized::class)
class AppConfigTest(private val path: String) {

    private val config = HoconApplicationConfig(ConfigFactory.load("application.conf"))

    @Test
    fun propertyExists() {
        val result = if (path == "ktor.application.modules") {
            getPropertyList(path).isNotEmpty()
        } else {
            getPropertyString(path).isNotBlank()
        }
        assertTrue(result, "$path should be present")
    }

    private fun getPropertyString(path: String): String = try {
        config.property(path).getString()
    } catch (exception: ApplicationConfigurationException) {
        ""
    }

    private fun getPropertyList(path: String): List<String> = try {
        config.property(path).getList()
    } catch (exception: ApplicationConfigurationException) {
        emptyList()
    }

    private companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): Collection<Array<String>> = listOf(
            arrayOf("ktor.deployment.port"),
            arrayOf("ktor.deployment.host"),
            arrayOf("ktor.application.modules"),
            arrayOf("postgres.url"),
            arrayOf("postgres.user"),
            arrayOf("postgres.password"),
            arrayOf("apiKey"),
            arrayOf("jwt.auth.secret"),
            arrayOf("jwt.unAuth.secret")
        )
    }
}
