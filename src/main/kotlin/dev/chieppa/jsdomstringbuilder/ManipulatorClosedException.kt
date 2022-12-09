package dev.chieppa.jsdomstringbuilder

class ManipulatorClosedException(val additionalInfo: String) :
    RuntimeException("This element has been closed for writing and cannot be manipulated. additionalInfo=$additionalInfo") {
}