package tk.empee.recipesManager.types;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class WorkbenchRecipe extends Recipe {

    public WorkbenchRecipe(String id, ItemStack result) {
        super(id, result);
    }

    public boolean isValid(ItemStack[] matrix) {
        int matrixLength = 0;
        int totalMatches = 0;

        for(int i=0; i<matrix.length; i++) {
            if(matrix[i] != null) {
                matrixLength++;
                ItemStack recipeItem = this.matrix.get(i);
                if(recipeItem != null) {
                    if(recipeItem.isSimilar(matrix[i])) {
                        if(matrix[i].getAmount() >= recipeItem.getAmount()) {
                            totalMatches++;
                        }
                    }
                }
            }
        }

        return matrixLength == totalMatches && totalMatches == this.matrix.size();
    }

    public void updateMatrix(ItemStack[] matrix) {

        for(int i=0; i<matrix.length; i++) {
            if(matrix[i] != null) {
                matrix[i].setAmount(
                        matrix[i].getAmount() - ( this.matrix.get(i).getAmount() - 1)
                );
            }
        }

    }

    public org.bukkit.inventory.Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(id, result);
        recipe.shape(shape);

        int slot = 0;
        for(String row : shape) {
            for(int i=0; i<row.length(); i++) {
                char ingredientKey = row.charAt(i);
                if(ingredientKey != ' ') {
                    ItemStack ingredient = ingredients.get(ingredientKey);
                    recipe.setIngredient(ingredientKey, ingredient.getType());
                    matrix.put(slot, ingredient);
                }

                slot += 1;
            }
        }

        return recipe;
    }

}
