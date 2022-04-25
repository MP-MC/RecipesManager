# RecipeManager
RecipeManager is a simple library that
allows you to customize the quantity of each ingredient
needed for the crafting.

## Features:
 - [x] Workbench recipes
 - [x] Recipes permission

## TODOs
 - [ ] Add support for other types of recipes
 - [ ] Add support for Shift-Click crafting

## How to use it
```java
public class MyPlugin extends JavaPlugin {
    
    @Override
    public void onEnable() {
        RecipesManager recipeManager = new RecipesManager(this);
        recipeManager.registerRecipe(getRecipe());
    }
    
    private Recipe getRecipe() {
        ItemStack item = new ItemStack(Material.BREAD);
        WorkbenchRecipe recipe = new WorkbenchRecipe("exampleID", item);

        recipe.setShape(
                "B B",
                " B ",
                "B B"
        );

        recipe.setIngredient('B', Material.BREAD, 64);

        return recipe;
    }
    
}
```