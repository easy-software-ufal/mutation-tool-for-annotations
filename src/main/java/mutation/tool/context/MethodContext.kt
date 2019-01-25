package mutation.tool.context

import com.github.javaparser.ast.body.MethodDeclaration

class MethodContext(val entity: MethodDeclaration):Context {
    override fun getInsertionPoint(): InsertionPoint = InsertionPoint.METHOD
}