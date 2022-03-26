package tk.empee.recipesManager.types;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Recipe {

    protected final static Plugin plugin = JavaPlugin.getProvidingPlugin(Recipe.class);

    @Getter
    protected final NamespacedKey id;
    @Getter
    protected final ItemStack result;
    @Getter @Setter
    private String permission;

    protected final HashMap<Character, ItemStack> ingredients = new HashMap<>();
    protected final HashMap<Integer, ItemStack> matrix = new HashMap<>();
    protected String[] shape;

    protected Recipe(String id, ItemStack result) {
        this.id = new NamespacedKey(plugin, id);
        this.result = result;
    }

    public void setShape(String... shape) {
        this.shape = shape;
    }

    public void setIngredient(char key, ItemStack item) {
        ingredients.put(key, item);
    }
    public void setIngredient(char key, Material material) {
        setIngredient(key, new ItemStack(material));
    }
    public void setIngredient(char key, Material material, int quantity) {
        setIngredient(key, new ItemStack(material, quantity));
    }

    public abstract boolean isValid(ItemStack[] inventory);
    public abstract void updateMatrix(ItemStack[] matrix);


    public Map<Integer, ItemStack> getMatrix() {
        return Collections.unmodifiableMap(matrix);
    }
    public abstract org.bukkit.inventory.Recipe getRecipe();

}
