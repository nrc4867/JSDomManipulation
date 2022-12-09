package dev.chieppa.jsdomstringbuilder

interface SetableJSLine: JSLine {

    fun setEqualTo(value: Escapable): String
}