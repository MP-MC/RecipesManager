package tk.empee.recipesManager;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import tk.empee.recipesManager.listeners.WorkbenchListener;

import java.util.ArrayList;
import java.util.Map;

public final class RecipesManager {

    private final Plugin plugin;
    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipesManager(Plugin plugin) {
        this.plugin = plugin;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new WorkbenchListener(this), plugin);
    }

    public void registerRecipe(Recipe recipe) {
        if(Bukkit.getRecipe(recipe.getId()) != null){
            plugin.getLogger().info("Unregistered recipe " + recipe.getId());
            Bukkit.removeRecipe(recipe.getId());
        }

        Bukkit.addRecipe(recipe.getRecipe());
        recipes.add(recipe);
        plugin.getLogger().info("Registered recipe " + recipe.getId());
    }

    //Retrieve a recipe only looking the materials of the matrix
    public Recipe getRecipe(ItemStack[] matrix) {

        Recipe value = null;

        for(Recipe recipe : recipes) {
            Map<Integer, ItemStack> recipeMatrix = recipe.getMatrix();
            value = recipe;
            for(int i=0; i<matrix.length; i++) {
                ItemStack item = recipeMatrix.get(i);
                if(matrix[i] != item) {
                    if(matrix[i] == null || item == null || matrix[i].getType() != item.getType()) {
                        value = null;
                        break;
                    }
                }
            }
        }

        return value;
    }

}
