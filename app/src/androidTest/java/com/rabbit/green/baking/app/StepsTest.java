package com.rabbit.green.baking.app;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.util.Collections;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class StepsTest {
    @Rule
    public IntentsTestRule<StepsActivity> rule =
            new IntentsTestRule<>(StepsActivity.class, false, false);

    @Before
    public void before() {
        Step step = new Step();
        step.setDescription("test");
        step.setShortDescription("test");
        step.setId(1);
        step.setVideoURL("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

        Ingredient ingredient = new Ingredient();
        ingredient.setQuantity(1);
        ingredient.setMeasure("test");
        ingredient.setIngredient("test");

        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Test");
        recipe.setServings(2);
        recipe.setSteps(Collections.singletonList(step));
        recipe.setIngredients(Collections.singletonList(ingredient));

        Intent intent = new Intent();
        intent.putExtra(StepsActivity.BUNDLE_KEY_RECIPE, Parcels.wrap(recipe));

        rule.launchActivity(intent);
    }

    @Test
    public void recipes_visible() {
        onView(withId(R.id.steps_rv))
                .perform(scrollToPosition(1))
                .check(matches(hasDescendant(withId(R.id.step_desc_tv))));

    }
}
