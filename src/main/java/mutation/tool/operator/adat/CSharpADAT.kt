package mutation.tool.operator.adat

import mutation.tool.context.Context
import mutation.tool.mutant.CSharpMutant
import mutation.tool.mutant.CSharpMutateVisitor
import mutation.tool.operator.CSharpOperator
import java.io.File

class CSharpADAT(context: Context, file: File) : CSharpOperator(context, file) {
    override val mutateVisitor = CSharpMutateVisitor(this)

    lateinit var map:Map<String, List<Map<String, String>>>

    override fun checkContext(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mutate(): List<CSharpMutant> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}