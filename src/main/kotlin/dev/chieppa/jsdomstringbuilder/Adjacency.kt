package dev.chieppa.jsdomstringbuilder

enum class Adjacency(val jsRepresentation: String) {
    BEFORE_BEGIN("beforebegin"),
    AFTER_BEGIN("afterBegin"),
    BEFORE_END("beforeend"),
    AFTER_END("afterend")
}