package dev.chieppa.jsdomstringbuilder

import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.appendJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.classListJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.createElementJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.getAttributeJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.innerHtmlJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.insertAdjacentElementJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.insertAdjacentHTMLJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.insertAdjacentTextJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.outerHtmlJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.prependJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.removeJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.setAttributeJS
import dev.chieppa.jsdomstringbuilder.ElementManipulator.ElementJS.tagNameJS

class ElementManipulator private constructor(list: MutableList<String> = mutableListOf()) :
    DocumentElementCommon(list) {

    fun innerHTML(): SetableJSLine {
        checkActionLock("innerHTML") {
            commands.add(innerHtmlJS())
        }
        return this
    }

    fun outerHTML(): SetableJSLine {
        checkActionLock("outerHTML") {
            commands.add(outerHtmlJS())
        }
        return this
    }

    fun tagName(): JSLine {
        checkActionLock("tagName") {
            commands.add(tagNameJS())
        }
        return this
    }

    fun classList() : DomTokenListManipulator {
        checkActionLock("classList") {
            commands.add(classListJS())
        }
        return DomTokenListManipulator.fromCommandReference(commands)
    }

    fun setAttribute(name: Escapable, value: Escapable): JSLine {
        checkActionLock("setAttribute") {
            commands.add(setAttributeJS(name.toString(), value.toString()))
        }
        return this
    }

    fun setId(value: String): JSLine {
        return setAttribute(Escapable("id"), Escapable(value))
    }

    fun setName(value: String): JSLine {
        return setAttribute(Escapable("name"), Escapable(value))
    }

    fun setClassList(classes: List<String>): JSLine {
        return setAttribute(Escapable("class"), Escapable(classes.joinToString(" ")))
    }

    fun getAttribute(name: Escapable): JSLine {
        checkActionLock("getAttribute") {
            commands.add(getAttributeJS(name.toString()))
        }
        return this
    }

    fun remove(): JSLine {
        checkActionLock("remove") {
            commands.add(removeJS())
        }
        return this
    }

    fun append(escapable: List<Escapable>): JSLine {
        checkActionLock("append") {
            commands.add(appendJS(escapable.joinToString(", ")))
        }
        return this
    }

    fun prepend(escapable: List<Escapable>): JSLine {
        checkActionLock("prepend") {
            commands.add(prependJS(escapable.joinToString(", ")))
        }
        return this
    }

    fun insertAdjacentElement(position: Adjacency, elementRef: String): JSLine {
        checkActionLock("insertAdjacentElement") {
            commands.add(insertAdjacentElementJS(position.jsRepresentation, elementRef))
        }
        return this
    }

    fun insertAdjacentElement(position: Adjacency, elementManipulator: ElementManipulator): JSLine {
        checkActionLock("insertAdjacentElement") {
            commands.add(
                insertAdjacentElementJS(
                    position.jsRepresentation, elementManipulator.endL(false)
                )
            )
        }
        return this
    }

    fun insertAdjacentHTML(position: Adjacency, text: Escapable): JSLine {
        checkActionLock("insertAdjacentHTML") {
            commands.add(insertAdjacentHTMLJS(position.jsRepresentation, text.toString()))
        }
        return this
    }

    fun insertAdjacentText(where: Adjacency, data: Escapable): JSLine {
        checkActionLock("insertAdjacentText") {
            commands.add(insertAdjacentTextJS(where.jsRepresentation, data.toString()))
        }
        return this
    }

    companion object {
        fun createElementByTag(tag: Escapable): ElementManipulator {
            val el = ElementManipulator()
            el.commands.add(createElementJS(tag.toString()))
            return el
        }

        fun elementByObjectReference(reference: String): ElementManipulator {
            val el = ElementManipulator()
            el.commands.add(reference)
            return el
        }

        internal fun fromCommandReference(commands: MutableList<String>): ElementManipulator {
            return ElementManipulator(commands)
        }
    }

    object ElementJS {
        fun createElementJS(tagName: String) = "document.createElement($tagName)"
        fun setAttributeJS(name: String, value: String) = "setAttribute($name, $value)"
        fun removeJS() = "remove()"
        fun getAttributeJS(attributeName: String) = "getAttribute($attributeName)"
        fun appendJS(param: String) = "append($param)"
        fun prependJS(param: String) = "prepend($param)"

        fun innerHtmlJS() = "innerHTML"
        fun outerHtmlJS() = "outerHTML"
        fun classListJS() = "classList"
        fun tagNameJS() = "tagName"

        fun insertAdjacentElementJS(position: String, element: String) = "insertAdjacentElement($position, $element)"
        fun insertAdjacentHTMLJS(position: String, text: String) = "insertAdjacentHTML($position, $text)"
        fun insertAdjacentTextJS(where: String, data: String) = "insertAdjacentText($where, $data)"
    }
}