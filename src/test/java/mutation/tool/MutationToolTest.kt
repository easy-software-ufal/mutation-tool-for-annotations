package mutation.tool

import mutation.tool.util.MutationToolConfig
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

private const val sourcePath = "./src/test/resources/fakeProject/src/main/java"
private const val testPath = "./src/test/resources/fakeProject/src/test/java"

internal class MutationToolTest {

    @Test
    fun runPathSourceNotExists() {
        assertTrue(File(testPath).exists())
        val config = MutationToolConfig(File(""), File(testPath))
        assertFalse(MutationTool(config).run())
    }

    @Test
    fun runPathTestNotExists() {
        assertTrue(File(sourcePath).exists())
        val config = MutationToolConfig(File(sourcePath), File(""))
        assertFalse(MutationTool(config).run())
    }

}