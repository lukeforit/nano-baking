package com.rabbit.green.baking.app;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.rabbit.green.baking.app.common.BakingApp;
import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipesTest {

    private IdlingResource idlingResource;

    @Rule
    public IntentsTestRule<SelectRecipeActivity> rule =
            new IntentsTestRule<>(SelectRecipeActivity.class);

    @Before
    public void setup() {
        idlingResource = OkHttp3IdlingResource.create("okHttpRS",
                ((BakingApp)
                        rule.getActivity().getApplication())
                        .getOkHttpClient());
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void finish() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    @Test
    public void recipes_visible() {
        onView(withId(R.id.recipes_rv))
                .perform(scrollToPosition(1))
                .check(matches(hasDescendant(withId(R.id.step_desc_tv))));

    }

    @Test
    public void recipes_selectSuccessful() {
        onView(withId(R.id.recipes_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(StepsActivity.class.getName()));

    }
}
