package com.rabbit.green.baking.app;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.source.local.RecipesContentProvider;
import com.rabbit.green.baking.app.data.source.local.RecipesContract;
import com.rabbit.green.baking.app.data.source.local.RecipesLocalDataStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RecipesContentProviderTest extends ProviderTestCase2<RecipesContentProvider> {

    private RecipesLocalDataStore cacheManager;

    /**
     * Constructor.
     */
    public RecipesContentProviderTest() {
        super(RecipesContentProvider.class, RecipesContract.AUTHORITY);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
        cacheManager = new RecipesLocalDataStore(getMockContentResolver());
    }

    @Test
    public void testInsert() {
        int recipeId = 999;
        String recipeName = "Test";

        String ingr1 = "Name 1";
        String ingr2 = "Name 2";

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName(recipeName);

        Ingredient ingredient1 = new Ingredient();

        ingredient1.setIngredient(ingr1);
        ingredient1.setMeasure("Measure 1");
        ingredient1.setQuantity(11.11);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredient(ingr2);
        ingredient2.setMeasure("Measure 2");
        ingredient2.setQuantity(22.22);

        recipe.setIngredients(Arrays.asList(ingredient1, ingredient2));

        cacheManager.saveRecipeIngredients(recipe);

        List<Recipe> recipes = cacheManager.getRecipes();
        assertFalse(recipes.isEmpty());
        assertEquals(recipeId, recipes.get(0).getId());
        assertEquals(recipeName, recipes.get(0).getName());

        List<Ingredient> ingredients = cacheManager.getIngredients(recipe.getId());
        assertFalse(ingredients.isEmpty());
        assertEquals(2, ingredients.size());
        assertEquals(ingr1, ingredients.get(0).getIngredient());
        assertEquals(ingr2, ingredients.get(1).getIngredient());
    }
}
