package tk.empee.recipesManager.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import tk.empee.recipesManager.RecipesManager;
import tk.empee.recipesManager.types.Recipe;

public final class WorkbenchListener implements Listener {

    private final RecipesManager recipesManager;

    public WorkbenchListener(RecipesManager recipesManager) {
        this.recipesManager = recipesManager;
    }

    @EventHandler
    public void onPlayerPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();

        if(event.getInventory().getResult() != null) {
            Player player = (Player) event.getView().getPlayer();
            ItemStack[] matrix = inventory.getMatrix();

            Recipe recipe = recipesManager.getRecipe(matrix);
            if (recipe != null) {
                if (recipe.getPermission() != null && !player.hasPermission(recipe.getPermission())) {
                    inventory.setResult(null);
                } else if (!recipe.isValid(matrix)) {
                    inventory.setResult(null);
                }
            }
        }

    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {

        CraftingInventory inventory = event.getInventory();
        ItemStack[] matrix = inventory.getMatrix();
        Recipe recipe = recipesManager.getRecipe(matrix);
        if (recipe != null) {
            ClickType click = event.getClick();

            if (inventory.getResult() == null) {
                event.setCancelled(true);
            } else if(click.isShiftClick() || click.isKeyboardClick()) {
                //TODO: IMPLEMENT
                event.setCancelled(true);
            } else {
                recipe.updateMatrix(matrix);
            }
        }

    }

}
