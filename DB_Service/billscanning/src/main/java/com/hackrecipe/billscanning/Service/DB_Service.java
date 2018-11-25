package com.hackrecipe.billscanning.Service;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.Index;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.iterators.IndexIterable;
import com.algolia.search.objects.Query;
import com.hackrecipe.billscanning.model.Ingredient;
import com.hackrecipe.billscanning.model.IngredientStock;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DB_Service {

    private static Map<String, Integer> ingredientMap = new HashMap<String, Integer>();
    private static APIClient client = new ApacheAPIClientBuilder("ZV6V83N86C", "").build();
    private static Index<Ingredient> indexIngredient = client.initIndex("ingredients", Ingredient.class);
    private static Index<IngredientStock> indexStock = client.initIndex("stock", IngredientStock.class);

    public static void main(String[] args) {

        initialiseMapTest();
        cleaningIngredientMap();
        addStocks();
    }

    private static void initialiseMapTest() {
        ingredientMap.put("potato", 2);
        ingredientMap.put("tomato", 5);
        ingredientMap.put("chicken", 1);
        ingredientMap.put("topinambour", 2);
    }

    private static void cleaningIngredientMap() {
        Iterator<String> it = ingredientMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (!ingredientExist(key)) {
                it.remove();
                System.out.println("Removed from map: " + key);
            }
        }
    }

    private static boolean ingredientExist(String key) {
        IndexIterable<Ingredient> ingredients = indexIngredient.browse(new Query(""));
        List<Ingredient> ingredientList = ingredients.stream().collect(Collectors.toList());
        return (ingredientList.contains(new Ingredient().setText(key)));

    }

    public static void addStocks() {
        Iterator<String> it = ingredientMap.keySet().iterator();
        List<IngredientStock> ingredientStocks = indexStock.browse(new Query()).stream().collect(Collectors.toList());
        while (it.hasNext()){
            String key = it.next();
            IngredientStock ingredient = new IngredientStock().setText(key);
            try {
                if(ingredientStocks.contains(ingredient)) {
                    int indexIngredient = ingredientStocks.indexOf(ingredient);
                    int actualQuantity = ingredientStocks.get(indexIngredient).getQuantity();

                    System.out.println(ingredientStocks.get(indexIngredient).getObjectIDFake());

                    indexStock.addObject(ingredientStocks.get(indexIngredient).setQuantity(actualQuantity+ ingredientMap.get(key)));
                }else{


                    indexStock.addObject(new IngredientStock().setText(key).setQuantity(ingredientMap.get(key)));

                }
            } catch (AlgoliaException e) {
                e.printStackTrace();
            }

        }

    }


    public String getStock() {
        try {
            String string = indexStock.search(new Query("").setAttributesToRetrieve(Arrays.asList("text"))).toString();
            return string;
        } catch (AlgoliaException e) {
            e.printStackTrace();
        }
        return "";
    }

}
