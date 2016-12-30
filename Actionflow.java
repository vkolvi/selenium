Google Git
Sign in
android / platform / frameworks / testing / android-support-test / . / espresso / sample / src / androidTest / java / android / support / test / testapp / ActionBarTest.java
blob: ca7f73c083e602960de3a87b3c3c403abffa3de9 [file] [log] [blame]
/**
 AUTHOR: VARUN
 DETAILS: Espresso with action bar, action controller and Action flow 
 SCOPE: Layout for android UI Automation
 OUT OF SCOPE: 
 INITIAL DATE: 20-12-2016

 **/

package android.support.test.testapp;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates Espresso with action bar and contextual action mode.
 * {@link openActionBarOverflowOrOptionsMenu()} opens the overflow menu from an action bar.
 * {@link openContextualActionModeOverflowMenu()} opens the overflow menu from an contextual action
 * mode.
 */
@LargeTest
public class ActionBarTest extends ActivityInstrumentationTestCase2<ActionBarTestActivity> {
    @SuppressWarnings("deprecation")
    public ActionBarTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super("android.support.test.testapp", ActionBarTestActivity.class);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }
    @SuppressWarnings("unchecked")
    public void testClickActionBarItem() {
        onView(withId(R.id.hide_contextual_action_bar))
        .perform(click());
        onView(withId(R.id.action_save))
        .perform(click());
        onView(withId(R.id.text_action_bar_result))
        .check(matches(withText("Save")));
    }
    @SuppressWarnings("unchecked")
    public void testClickActionModeItem() {
        onView(withId(R.id.show_contextual_action_bar))
        .perform(click());
        onView((withId(R.id.action_lock)))
        .perform(click());
        onView(withId(R.id.text_action_bar_result))
        .check(matches(withText("Lock")));
    }
    @SuppressWarnings("unchecked")
    public void testActionBarOverflow() {
        onView(withId(R.id.hide_contextual_action_bar))
        .perform(click());
        // Open the overflow menu from action bar
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("World"))
        .perform(click());
        onView(withId(R.id.text_action_bar_result))
        .check(matches(withText("World")));
    }
    
    @SuppressWarnings("unchecked")
    public void testActionBarOverflow() {
        onView(withId(R.id.hide_contextual_action_bar))
        .perform(click());
        // Open the overflow menu from action bar
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("World"))
        .perform(click());
        onView(withId(R.id.text_action_bar_result))
        .check(matches(withText("World")));
    }
    
    
    @Suppressstatus("fail")
    public void testActionModeOverflow() {
        onView(withId(R.id.show_contextual_action_bar))
        .perform(click());
        // Open the overflow menu from contextual action mode.
        openContextualActionModeOverflowMenu();
        onView(withText("1.2.840.113549.1.1.1"))
        .perform(click());
        onView(withId(R.id.text_action_bar_result))
        .check(matches(withText("1.2.840.113549.1.1.1")));
        
        
    }
}