package com.example.loginscreen;

/**
 * Mockito test for Add/Remove contacts page
 * @author Aaron Goff
 */
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertEquals;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.loginscreen.ui.login.ContactsModActivity;
import com.example.loginscreen.ui.login.ContactsActivity;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ContactsModTest {

    @Rule
    public IntentsTestRule<ContactsModActivity> activityRule = new IntentsTestRule<>(ContactsModActivity.class);


    @Test
    public void pushNewContactWithTestUser()
    {
        Intent result = new Intent();
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        String un = "asd";
        String pass = "asd";
        String cont = "mockTest";
        onView(withId(R.id.username)).perform(typeText(un), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withText(R.id.contactTextBox)).perform(typeText(cont), closeSoftKeyboard());

        onView(withId(R.id.addContactButton)).perform(click());

        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
        }

        intended(hasComponent(ContactsActivity.class.getName()));
    }

    @Test
    public void pushNewContactWithTestUser()
    {
        Intent result = new Intent();
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        String un = "asd";
        String pass = "asd";
        String cont = "mockTest";
        onView(withId(R.id.username)).perform(typeText(un), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withText(R.id.contactTextBox)).perform(typeText(cont), closeSoftKeyboard());

        onView(withId(R.id.removeContactButton)).perform(click());

        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
        }

        intended(hasComponent(ContactsActivity.class.getName()));
    }
}
