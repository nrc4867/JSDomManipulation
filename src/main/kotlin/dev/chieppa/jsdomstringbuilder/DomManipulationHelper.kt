package dev.chieppa.jsdomstringbuilder

object DomManipulationHelper {

    fun forEachIndexed(iteratorName: String, listReference: String, methodBody: String) : String =
"""
for(let $iteratorName = 0; $iteratorName < $listReference.length; $iteratorName++) { $methodBody } 
""".trimIndent()

    fun wrapCommands(commands: String) =
"""
(() => { $commands })(); 
""".trimIndent()

    fun ifHelper(condition: String, body: String, elseBody: String = "") =
"""
if($condition) { $body } else { $elseBody } 
""".trimIndent()

}