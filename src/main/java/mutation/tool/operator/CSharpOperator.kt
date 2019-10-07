package mutation.tool.operator

import mutation.tool.context.*
import mutation.tool.mutant.CSharpMutant
import mutation.tool.mutant.CSharpMutateVisitor
import mutation.tool.util.*
import mutation.tool.util.xml.codeToDocument
import mutation.tool.util.xml.fileToDocument
import org.w3c.dom.Node
import java.io.File

abstract class CSharpOperator(val context:Context, val file:File):Operator {
    abstract val mutateVisitor:CSharpMutateVisitor
    private val rootNode = fileToDocument(file, Language.C_SHARP).childNodes.item(0)
    private var locked = false

    /**
     * Check if this operator can be applicable to context
     * @return true if can be aplicable
     */
    abstract override fun checkContext():Boolean

    /**
     * Generate mutants
     *
     * @return a list of mutants
     */
    abstract fun mutate():List<CSharpMutant>

    /**
     * Visit and change the ast of the source file
     *
     * @return the changed compilation unit (representation of the ast)
     */
    protected fun visit(): Node {
        val newRootNode = this.rootNode.cloneNode(true)
        this.unlock()
        mutateVisitor.visit(newRootNode)
        return newRootNode
    }

    open fun visitClass(node:Node):Boolean {
        return (!locked && isSameClass(context, node))
    }

    open fun visitProperty(node:Node):Boolean {
        return (!locked && isSameProp(context, node))
    }

    open fun visitMethod(node:Node):Boolean {
        return (!locked && isSameMethod(context, node))
    }

    open fun visitParameter(node:Node):Boolean {
        return (!locked && isSameParameter(context, node))
    }

    /**
     * allow actions when visiting an ast
     */
    override fun unlock() { locked = false }

    /**
     * preventing actions when visiting an ast
     */
    override fun lock() { locked = true }

    protected fun getAnnotations(node: Node, insertionPoint: InsertionPoint): List<Node> = when(insertionPoint) {
        InsertionPoint.CLASS -> convertAnnotations(ClassContext(node))
        InsertionPoint.METHOD -> convertAnnotations(MethodContext(node))
        InsertionPoint.PROPERTY -> convertAnnotations(PropertyContext(node))
        InsertionPoint.PARAMETER -> convertAnnotations(ParameterContext(node))
    }

    private fun convertAnnotations(context: Context): MutableList<Node> {
        val annotations = mutableListOf<Node>()
        for (annotation in context.annotations) {
            annotations += codeToDocument(annotation.string, Language.C_SHARP).childNodes.item(0)
        }

        return annotations
    }
}

fun getValidCSharpOperators(contexts: List<Context>, cSharpFile: File, config: MutationToolConfig):List<CSharpOperator> {
    val validOperators = mutableListOf<CSharpOperator>()
    val operatorsEnum = config.operators
    val factory = OperatorFactory(config)

    for (operatorEnum in operatorsEnum) validOperators += factory.getCSharpOperators(operatorEnum, contexts, cSharpFile)

    return validOperators
}