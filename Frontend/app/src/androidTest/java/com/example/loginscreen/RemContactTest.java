//package com.example.loginscreen;
//
//import android.app.Activity;
//import android.app.Instrumentation;
//import android.content.Intent;
//
//import androidx.test.espresso.intent.rule.IntentsTestRule;
//import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
//
//import com.example.loginscreen.ui.login.LoginActivity;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.intent.Intents.intended;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//@RunWith(AndroidJUnit4ClassRunner.class)
//public class RemContactTest {
//    @Rule
//    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);
//    @Test
//    public void testRegisterNewUser() throws InterruptedException {
//        Intent result = new Intent();
//        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);
//
//        String user = "mockRegisterTest";
//        String pass = "mockTest";
//        String contact = "bob";
//
//        onView(withId(R.id.username)).perform(typeText(user), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
//        Thread.sleep(500);
//
//        onView(withId(R.id.login)).perform(click());
//        Thread.sleep(1000);
//        onView(withId(R.id.contactsButton)).perform(click());
//        Thread.sleep(1000);
//        onView(withId(R.id.modifyButton)).perform(click());
//        Thread.sleep(1000);
//
//        onView(withId(R.id.contactTextBox)).perform(typeText(contact), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.removeContactButton)).perform(click());
//        Thread.sleep(1000);
//
//    }
//
//}