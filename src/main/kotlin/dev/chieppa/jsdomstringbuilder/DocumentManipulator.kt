package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.DocumentManipulator.DocumentJS.bodyJS
import dev.chieppa.jsdomstringbuilder.DocumentManipulator.DocumentJS.getElementByIdJS
import dev.chieppa.jsdomstringbuilder.DocumentManipulator.DocumentJS.headJS

class DocumentManipulator private constructor(list: MutableList<String> = mutableListOf()) : DocumentElementCommon(list) {

    fun getElementById(id: Escapable): ElementManipulator {
        checkActionLock("getElementById") {
            commands.add(getElementByIdJS(id.toString()))
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    fun head(): ElementManipulator {
        checkActionLock("head") {
            commands.add(headJS())
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    fun body(): ElementManipulator {
        checkActionLock("body") {
            commands.add(bodyJS())
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    companion object {
        fun getDocument(): DocumentManipulator {
            val doc = DocumentManipulator()
            doc.commands.add("document")
            return doc
        }
    }

    object DocumentJS {
        fun getElementByIdJS(id: String) = "getElementById($id)"
        fun headJS() = "head"
        fun bodyJS() = "body"
    }

}