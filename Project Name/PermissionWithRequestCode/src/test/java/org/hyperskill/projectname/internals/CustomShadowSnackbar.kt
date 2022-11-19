package org.hyperskill.projectname.internals

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.snackbar.ContentViewCallback
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import org.robolectric.shadow.api.Shadow
import org.robolectric.util.ReflectionHelpers

@Implements(Snackbar::class)
class CustomShadowSnackbar {

    companion object {
        val shownSnackbars = mutableListOf<Snackbar>()

        fun Snackbar.getTextMessage(): String {

            val view = (this.view as ViewGroup)
                .children
                .first { it is SnackbarContentLayout } as SnackbarContentLayout

            return view.messageView.text.toString()
        }

        fun clear() {
            shownSnackbars.clear()
        }
    }

    @RealObject
    lateinit var snackbar: Snackbar

    @Implementation
    fun show() {
        shownSnackbars.add(snackbar)
    }

    @Implementation
    fun __constructor__(
        context: Context,
        parent: ViewGroup,
        content: View,
        contentViewCallback: ContentViewCallback) {

        Shadow.invokeConstructor(
            Snackbar::class.java,
            snackbar,
            ReflectionHelpers.ClassParameter(Context::class.java, context),
            ReflectionHelpers.ClassParameter(ViewGroup::class.java, parent),
            ReflectionHelpers.ClassParameter(View::class.java, content),
            ReflectionHelpers.ClassParameter(ContentViewCallback::class.java, contentViewCallback)
        )
    }
}