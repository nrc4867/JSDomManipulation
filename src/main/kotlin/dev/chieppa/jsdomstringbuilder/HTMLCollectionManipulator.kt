package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.HTMLCollectionManipulator.HTMLCollectionJS.itemJS
import dev.chieppa.jsdomstringbuilder.HTMLCollectionManipulator.HTMLCollectionJS.lengthJS
import dev.chieppa.jsdomstringbuilder.HTMLCollectionManipulator.HTMLCollectionJS.namedItemJS

class HTMLCollectionManipulator private constructor(list: MutableList<String> = mutableListOf()) : Manipulator(list) {

    fun length() : JSLine {
        checkActionLock("length") {
            commands.add(lengthJS())
        }
        return this
    }

    fun item(index: Int): ElementManipulator {
        checkActionLock("item") {
            commands.add(itemJS(index))
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    fun namedItem(name: String): ElementManipulator {
        checkActionLock("namedItem") {
            commands.add(namedItemJS(name))
        }
        return ElementManipulator.fromCommandReference(commands)
    }

    companion object {
        fun htmlCollectionFromReference(reference: String): HTMLCollectionManipulator {
            val collection = HTMLCollectionManipulator()
            collection.commands.add(reference)
            return collection
        }

        internal fun fromCommandReference(commands: MutableList<String>): HTMLCollectionManipulator {
            return HTMLCollectionManipulator(commands)
        }
    }

    object HTMLCollectionJS {
        fun lengthJS() = "length"
        fun itemJS(index: Int) = "item($index)"
        fun namedItemJS(name: String) = "namedItem(`$name`)"
    }

}