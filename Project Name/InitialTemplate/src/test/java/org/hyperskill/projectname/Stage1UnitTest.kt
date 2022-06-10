package org.hyperskill.projectname

import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){

    @Test
    fun testSomething() {

        testActivity {
            fail()
        }
    }
}