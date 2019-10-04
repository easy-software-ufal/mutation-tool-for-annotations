package mutation.tool.operator.ada

import mutation.tool.context.Context
import mutation.tool.mutant.CSharpMutant
import mutation.tool.mutant.CSharpMutateVisitor
import mutation.tool.operator.CSharpOperator
import java.io.File

class CSharpADA(override val mutateVisitor: CSharpMutateVisitor, context: Context, file: File) :
        CSharpOperator(context, file) {
    override fun checkContext(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mutate(): List<CSharpMutant> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}