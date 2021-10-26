package task.acronyms.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import task.acronyms.R

@RunWith(AndroidJUnit4::class)
class AcronymFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<AcronymFragment>(
            themeResId = R.style.Theme_Acronyms
        )
    }

    @Test
    fun testAllViewDisplayed() {
        Espresso.onView(withId(R.id.tv_input))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.imb_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

}