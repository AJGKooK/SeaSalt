/*package com.example.loginscreen;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.loginscreen.ui.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class EventsTest {
    @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);


    @Test
    public void clickAddEvent() throws InterruptedException {
        Intent result = new Intent();
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        String un = "cjurenic1";
        String pass = "123123";
        onView(withId(R.id.username)).perform(typeText(un), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        Thread.sleep(1000);

        onView(withId(R.id.login)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.eventsButton)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.createButton)).perform(click());
        Thread.sleep(1000);
        String title = "Chandlers Event Test";
        String time = "1230";
        String desc = "First Mockio Test";

        onView(withId(R.id.eventTitle)).perform(typeText(title), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.timeEvent)).perform(typeText(time), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.descriptionEvent)).perform(typeText(desc), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.addEvent)).perform(click());
        Thread.sleep(1000);

    }


}*/
