package mutation.tool.operator.ada

import mutation.tool.annotation.getListOfAnnotationContext
import mutation.tool.mutant.Mutant
import mutation.tool.operator.FILE1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ADATest {

    @Test
    fun testADA() {
        val annotations = listOf(
                "@a",
                "@a(\"ww\")",
                "@a(5)",
                "@a(file=\"err\", name = \"ee\")"
        )

        val mutants = mutableListOf<Mutant>()
        for (context in getListOfAnnotationContext(File(FILE1))) {
            val operator = ADA(context, File(FILE1))
            operator.annotation = annotations[(0..(annotations.size - 1)).random()]
            mutants += operator.mutate()
        }

        assertEquals(19, mutants.size)
    }
}