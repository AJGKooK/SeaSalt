package com.example.loginscreen;

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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static org.hamcrest.core.StringEndsWith.endsWith;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.loginscreen.ui.login.LoginActivity;
import com.example.loginscreen.ui.login.MainActivity;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginTest {

    @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);


    @Test
    public void loginWithTestUser()
    {
        Intent result = new Intent(activityRule.getActivity(), MainActivity.class);
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        intending(toPackage("com.example.loginscreen.ui.login.MainActivity")).respondWith(activityResult);

        String un = "asd";
        String pass = "asd";
        onView(withId(R.id.username)).perform(typeText(un), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());

        onView(withId(R.id.login)).perform(click());

        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
        }


    }
}
