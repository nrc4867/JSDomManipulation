package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.DocumentElementCommon.DocumentElementCommonJS.childrenJS
import dev.chieppa.jsdomstringbuilder.DocumentElementCommon.DocumentElementCommonJS.getElementsByClassNameJS
import dev.chieppa.jsdomstringbuilder.DocumentElementCommon.DocumentElementCommonJS.getElementsByTagNameJS
import dev.chieppa.jsdomstringbuilder.DocumentElementCommon.DocumentElementCommonJS.querySelectorAllJS
import dev.chieppa.jsdomstringbuilder.DocumentElementCommon.DocumentElementCommonJS.querySelectorJS

abstract class DocumentElementCommon(list: MutableList<String>) : NodeManipulator(list) {

    fun querySelector(selectors: Escapable): ElementManipulator {
        checkActionLock("querySelector") {
            commands.add(querySelectorJS(selectors.toString()))
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    fun querySelectorAll(selectors: Escapable): NodeListManipulator {
        checkActionLock("querySelectorAll") {
            commands.add(querySelectorAllJS(selectors.toString()))
        }
        return NodeListManipulator.fromCommandReference(commands)
    }

    fun getElementsByClassName(names: Escapable): HTMLCollectionManipulator {
        checkActionLock("getElementsByClassName") {
            commands.add(getElementsByClassNameJS(names.toString()))
        }
        return HTMLCollectionManipulator.fromCommandReference(commands)
    }

    fun getElementsByTagName(name: Escapable): HTMLCollectionManipulator {
        checkActionLock("getElementsByTagName") {
            commands.add(getElementsByTagNameJS(name.toString()))
        }
        return HTMLCollectionManipulator.fromCommandReference(commands)
    }

    fun children(): HTMLCollectionManipulator {
        checkActionLock("children") {
            commands.add(childrenJS())
        }
        return HTMLCollectionManipulator.fromCommandReference(commands)
    }


    object DocumentElementCommonJS {
        fun querySelectorJS(selectors: String) = "querySelector($selectors)"
        fun querySelectorAllJS(selectors: String) = "querySelectorAll($selectors)"
        fun getElementsByClassNameJS(names: String) = "getElementsByClassName($names)"
        fun getElementsByTagNameJS(name: String) = "getElementsByTagName($name)"

        fun childrenJS() = "children"
    }

}