package com.example.moviehunter;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.moviehunter.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void searchMovies_displaysResults() throws InterruptedException {

        onView(withId(R.id.searchEditText))
                .perform(typeText("The Rip"), pressImeActionButton());

        Thread.sleep(3000);

        onView(withId(R.id.view1))
                .check(matches(hasDescendant(withText("The Rip"))));
    }
}
