package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.addJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.containsJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.itemJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.lengthJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.removeJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.replaceJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.toggleJS
import dev.chieppa.jsdomstringbuilder.DomTokenListManipulator.DomTokenListJS.valueJS

class DomTokenListManipulator private constructor(list: MutableList<String> = mutableListOf()) : Manipulator(list) {

    fun length(): JSLine {
        checkActionLock("length") {
            commands.add(lengthJS())
        }
        return this
    }

    fun value(): JSLine {
        checkActionLock("value") {
            commands.add(valueJS())
        }
        return this
    }

    fun item(index: Int): JSLine {
        checkActionLock("item") {
            commands.add(itemJS(index.toString()))
        }
        return this
    }

    fun contains(prop: Escape): JSLine {
        checkActionLock("contains") {
            commands.add(containsJS(prop.toString()))
        }
        return this
    }

    fun add(prop: Escape): JSLine {
        checkActionLock("addJS") {
            commands.add(addJS(prop.toString()))
        }
        return this
    }

    fun remove(prop: Escape): JSLine {
        checkActionLock("remove") {
            commands.add(removeJS(prop.toString()))
        }
        return this
    }

    fun toggle(prop: Escape): JSLine {
        checkActionLock("toggle") {
            commands.add(toggleJS(prop.toString()))
        }
        return this
    }

    fun toggle(prop: Escape, force: Boolean): JSLine {
        checkActionLock("toggle") {
            commands.add(toggleJS(prop.toString(), force))
        }
        return this
    }

    fun replace(oldToken: Escape, newToken: Escape): JSLine {
        checkActionLock("replace") {
            commands.add(replaceJS(oldToken.toString(), newToken.toString()))
        }
        return this
    }

    companion object {
        fun domTokenListRef(reference: String): DomTokenListManipulator {
            val domTokenListManipulator = DomTokenListManipulator()
            domTokenListManipulator.commands.add(reference)
            return domTokenListManipulator
        }

        internal fun fromCommandReference(commands: MutableList<String>): DomTokenListManipulator {
            return DomTokenListManipulator(commands)
        }
    }

    object DomTokenListJS {
        fun lengthJS() = "length"
        fun valueJS() = "value"

        fun itemJS(index: String) = "item($index)"
        fun containsJS(value: String) = "contains($value)"
        fun addJS(value: String) = "add($value)"
        fun removeJS(value: String) = "remove($value)"
        fun toggleJS(value: String) = "toggle($value)"
        fun toggleJS(value: String, force: Boolean) = "toggle($value, $force)"
        fun replaceJS(oldToken: String, newToken: String) = "replace($oldToken, $newToken)"
    }

}