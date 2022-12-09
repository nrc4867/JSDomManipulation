package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.NodeManipulator.NodeJS.removeChildJS
import dev.chieppa.jsdomstringbuilder.NodeManipulator.NodeJS.replaceChildJS

abstract class NodeManipulator constructor(list: MutableList<String> = mutableListOf()) : Manipulator(list) {

    fun removeChild(ref: NoEscape): NodeManipulator {
        commands.add(removeChildJS(ref.toString()))
        return this
    }

    fun replaceChild(newChild: NoEscape, oldChild: NoEscape): NodeManipulator {
        commands.add(replaceChildJS(newChild.toString(), oldChild.toString()))
        return this
    }

    object NodeJS {
        fun removeChildJS(child: String) = "removeChild($child)"
        fun replaceChildJS(newChild: String, oldChild: String) = "replaceChild($newChild, $oldChild)"
    }
}