package mutation.tool.util.xml

import org.w3c.dom.Node

abstract class NodeVisitor {

    protected open fun visitClass(node: Node) {}
    protected open fun visitMethod(node: Node) {}
    protected open fun visitProperty(node: Node) {}
    protected open fun visitParameter(node: Node) {}
    protected open fun visitAttribute(node: Node) {}

    fun visit(rootNode: Node) {
        when(rootNode.nodeName) {
            NodeType.CLASS.nodeName -> this.visitClass(rootNode)
            NodeType.METHOD.nodeName -> this.visitMethod(rootNode)
            NodeType.PARAMETER.nodeName -> this.visitParameter(rootNode)
            NodeType.PROPERTY.nodeName -> this.visitProperty(rootNode)
            NodeType.ATTRIBUTE.nodeName -> this.visitAttribute(rootNode)
        }

        for (index in 0 until rootNode.childNodes.length) {
            val node = rootNode.childNodes.item(index)
            this.visit(node)
        }
    }
}