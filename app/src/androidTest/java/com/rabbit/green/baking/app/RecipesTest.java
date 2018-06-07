package com.rabbit.green.baking.app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rabbit.green.baking.app.recipes.selection.SelectRecipeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipesTest {
    @Rule
    ActivityTestRule<SelectRecipeActivity> rule =
            new ActivityTestRule<>(SelectRecipeActivity.class);

    @Test
    public void test() {

    }
}
