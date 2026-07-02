package net.lsafer.ij.fun_file_icon

import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.KotlinIcons
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import javax.swing.Icon

class FunFileIconProvider : IconProvider(), DumbAware {
    private val composeIcon: Icon = IconLoader.getIcon("/icons/compose_multiplatform_icon.svg", this::class.java)

    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        if (element is KtFile) {
            for (declaration in element.declarations) {
                if (declaration is KtNamedFunction) {
                    val fileNameWithoutExtension = element.name.substringBeforeLast(".")

                    // Match the function name to the file name
                    if (declaration.name == fileNameWithoutExtension) {
                        // Return Compose logo if it is a composable function
                        if (declaration.annotationEntries.any { it.shortName?.asString() == "Composable" })
                            return composeIcon

                        // Return the standard IntelliJ Kotlin Function Icon
                        return KotlinIcons.FUNCTION
                    }
                }
                if (declaration is KtProperty) {
                    val fileNameWithoutExtension = element.name.substringBeforeLast(".")

                    // Match the property name to the file name
                    if (declaration.name == fileNameWithoutExtension) {
                        // Return the standard IntelliJ Kotlin VAR/VAL Icon
                        return if (declaration.isVar) KotlinIcons.VAR else KotlinIcons.VAL
                    }
                }
            }
        }

        // Fallback to default behavior
        return null
    }
}
