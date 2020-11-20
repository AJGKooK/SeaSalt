package com.example.loginscreen;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.loginscreen.ui.login.EventsActivity;
import com.example.loginscreen.ui.login.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.example.loginscreen.ui.login.LoginActivity;
import com.example.loginscreen.ui.login.MainActivity;
import com.example.loginscreen.ui.login.UserActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ChatTest {
   @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);
    @Test
    public void clickSendMessage() throws InterruptedException {
        Intent result = new Intent();
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        String user = "cjurenic";
        String pass = "123123";
        String message = "This is my chat mockito test";

        onView(withId(R.id.username)).perform(typeText(user), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        Thread.sleep(1000);

        onView(withId(R.id.login)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.chatButton)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.chatLog)).perform(typeText(message), closeSoftKeyboard());
        Thread.sleep(500);
        onView(withId(R.id.msgButton)).perform(click());
        Thread.sleep(1000);



    }
}
