/*package com.example.loginscreen;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.loginscreen.ui.login.LoginActivity;
import com.example.loginscreen.ui.login.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginTest {

    @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);


    @Test
    public void loginWithTestUser()
    {
        Intent result = new Intent();
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        String un = "asd";
        String pass = "asd";
        onView(withId(R.id.username)).perform(typeText(un), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());

        onView(withId(R.id.login)).perform(click());

        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
        }

        intended(hasComponent(MainActivity.class.getName()));
    }
}*/
