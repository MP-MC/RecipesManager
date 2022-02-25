package tk.empee.recipesManager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

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

    org.bukkit.inventory.Recipe getRecipe() {

        ShapedRecipe recipe = new ShapedRecipe(id, result);

        int slot = 0;
        String[] shape = new String[3];
        for(int i=0; i<3; i++) {
            StringBuilder row = new StringBuilder();
            for(int j=0; j<3; j++) {
                Character character = matrixMap.get(slot);
                if(character != null) {
                    row.append(character);
                } else {
                    row.append(" ");
                }
                slot += 1;
            }
            shape[i] = row.toString();
        }

        recipe.shape(shape);


        for(Map.Entry<Integer, Character> slotEntry : matrixMap.entrySet()) {
            ItemStack ingredient = ingredients.get(slotEntry.getValue());
            if(ingredient != null) {
                recipe.setIngredient(slotEntry.getValue(), ingredient.getType());
                this.matrix.put(slotEntry.getKey(), ingredient);
            }
        }

        return recipe;
    }

}
