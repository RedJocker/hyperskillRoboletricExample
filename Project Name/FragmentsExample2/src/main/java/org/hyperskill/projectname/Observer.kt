package org.hyperskill.projectname

interface Observer<T> {
    fun onNotify(observed: T)
}