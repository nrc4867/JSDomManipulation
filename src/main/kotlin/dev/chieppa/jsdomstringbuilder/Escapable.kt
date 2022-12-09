package dev.chieppa.jsdomstringbuilder

open class Escapable(open val item: String, val escape: Boolean = true, open val escapeChar: Char = '`') {
    override fun toString(): String {
        return if (escape) {
            "$escapeChar$item$escapeChar"
        } else {
            item
        }
    }
}

data class Escape(override val item: String, override val escapeChar: Char) : Escapable(item, true, escapeChar) {
    override fun toString(): String {
        return super.toString()
    }
}

data class NoEscape(override val item: String) : Escapable(item, false) {
    override fun toString(): String {
        return super.toString()
    }
}


fun String.escape(escapeChar: Char = '`') = Escape(this, escapeChar)
fun String.noEscape() = NoEscape(this)