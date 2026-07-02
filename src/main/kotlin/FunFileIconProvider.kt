package net.lsafer.ij.fun_file_icon

import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.KotlinIcons
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import javax.swing.Icon

class FunFileIconProvider : IconProvider(), DumbAware {
    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        if (element is KtFile) {
            for (declaration in element.declarations) {
                if (declaration is KtNamedFunction) {
                    val fileNameWithoutExtension = element.name.substringBeforeLast(".")

                    // Match the function name to the file name
                    if (declaration.name == fileNameWithoutExtension) {
                        // Return the standard IntelliJ Kotlin Function Icon
                        return KotlinIcons.FUNCTION
                    }
                }
            }
        }

        // Fallback to default behavior
        return null
    }
}
