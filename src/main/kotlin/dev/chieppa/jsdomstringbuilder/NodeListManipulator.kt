package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.NodeListManipulator.NodeListJS.forEachJS
import dev.chieppa.jsdomstringbuilder.NodeListManipulator.NodeListJS.itemJS
import dev.chieppa.jsdomstringbuilder.NodeListManipulator.NodeListJS.lengthJS

class NodeListManipulator private constructor(list: MutableList<String> = mutableListOf()) : Manipulator(list) {

    fun item(index: Long): ElementManipulator {
        checkActionLock("item") {
            commands.add(itemJS(index))
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    fun length(): JSLine {
        checkActionLock("length") {
            commands.add(lengthJS())
        }
        return this
    }

    fun forEach(
        methodBody: String,
        currentValueName: String = "value",
        currentIndexName: String = "index",
        currentListName: String = "listObj",
        callback: String = "''",
    ): JSLine {
        checkActionLock("forEach") {
            commands.add(forEachJS(methodBody, currentValueName, currentIndexName, currentListName, callback))
        }
        return this
    }

    companion object {
        fun nodeListByReference(reference: String): NodeListManipulator {
            val nodeList = NodeListManipulator()
            nodeList.commands.add(reference)
            return nodeList
        }

        internal fun fromCommandReference(commands: MutableList<String>): NodeListManipulator {
            return NodeListManipulator(commands)
        }
    }

    object NodeListJS {
        fun itemJS(index: Long) = "item($index)"
        fun lengthJS() = "length"

        fun forEachJS(
            methodBody: String,
            currentValueName: String,
            currentIndexName: String,
            currentListName: String,
            callback: String
        ) = "forEach(function($currentValueName, $currentIndexName, $currentListName) { $methodBody }, $callback)"
    }

}