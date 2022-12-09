package dev.chieppa.jsdomstringbuilder

interface JSLine {
    fun endL(semicolon: Boolean = true): String

    fun setAs(objIdentifier: String,
              firstDef: Boolean = true,
              redefinable: Boolean = false,
              semicolon: Boolean = true
    ): String
}