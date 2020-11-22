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
//import com.example.loginscreen.ui.login.MainActivity;
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
//public class RegisterTest {
//    @Rule
//    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);
//    @Test
//    public void testRegisterNewUser() throws InterruptedException {
//        Intent result = new Intent();
//        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);
//
//        String user = "mockRegisterTest";
//        String pass = "mockTest";
//        String passVer = "mockTest";
//        String firstN = "Aaron";
//        String lastN = "G";
//        String accR = "student";
//
//        onView(withId(R.id.newaccount)).perform(click());
//        Thread.sleep(1000);
//
//        onView(withId(R.id.username)).perform(typeText(user), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.password_verify)).perform(typeText(passVer), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.name_first)).perform(typeText(firstN), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.name_last)).perform(typeText(lastN), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.accRole)).perform(typeText(accR), closeSoftKeyboard());
//        Thread.sleep(500);
//
//        onView(withId(R.id.submit)).perform(click());
//        Thread.sleep(1000);
//
//        onView(withId(R.id.username)).perform(typeText(user), closeSoftKeyboard());
//        Thread.sleep(500);
//        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
//        Thread.sleep(500);
//
//        onView(withId(R.id.login)).perform(click());
//        Thread.sleep(1000);
//
//        intended(hasComponent(MainActivity.class.getName()));
//
//
//    }
//}
