package mutation.tool.operator.chodr

import mutation.tool.annotation.getListOfAnnotationContext
import mutation.tool.mutant.Mutant
import mutation.tool.operator.FILE1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class CHODRTest {

    @Test
    fun testCHODR() {
        val mutants = mutableListOf<Mutant>()

        for(context in getListOfAnnotationContext(File(FILE1))) {
            val operator = CHODR(context, File(FILE1))
            if (operator.checkContext())
                mutants += operator.mutate()
        }

        assertEquals(1, mutants.size)
    }
}