package dev.chieppa.jsdomstringbuilder

abstract class Manipulator (protected val commands: MutableList<String>): SetableJSLine {

    private var writable : String?  = null

    protected fun lockManipulator(reason: String) {
        writable = reason
    }

    protected fun checkWritable() {
        writable?.let {
            throw ManipulatorClosedException(it)
        }
    }

    protected fun checkActionLock(reason: String, action: () -> Unit) {
        checkWritable()
        action()
        lockManipulator(reason)
    }

    override fun endL(semicolon: Boolean): String {
        return commands.joinToString(".") + if (semicolon) ";" else ""
    }

    override fun setAs(objIdentifier: String, firstDef: Boolean, redefinable: Boolean, semicolon: Boolean): String {
        val ret = if (firstDef) {
            if (redefinable) {
                "let "
            } else {
                "const "
            }
        } else {
            ""
        }

        return ret + objIdentifier + " = " + endL(semicolon)
    }

    override fun setEqualTo(value: Escapable): String {
        return "${commands.joinToString(".")} = $value;"
    }

    override fun toString(): String {
        return endL()
    }

}