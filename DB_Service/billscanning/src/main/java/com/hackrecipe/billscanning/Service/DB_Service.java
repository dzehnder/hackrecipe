package com.hackrecipe.billscanning.Service;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.Index;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.iterators.IndexIterable;
import com.algolia.search.objects.Query;
import com.hackrecipe.billscanning.model.IngredientGen;
import com.hackrecipe.billscanning.model.IngredientStockGen;
import com.hackrecipe.billscanning.model.IngredientStockUpd;
import com.hackrecipe.billscanning.model.IngredientUpd;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DB_Service {

    private static Map<String, Integer> ingredientMap = new HashMap<String, Integer>();
    private static APIClient client = new ApacheAPIClientBuilder("ZV6V83N86C", "46ce68dc64abc23969dd37096bd5268e").build();
    private static Index<IngredientUpd> indexIngredientUpd = client.initIndex("ingredients", IngredientUpd.class);
    private static Index<IngredientStockUpd> indexStockUpd = client.initIndex("stock", IngredientStockUpd.class);
    private static Index<IngredientGen> indexIngredientGen = client.initIndex("ingredients", IngredientGen.class);
    private static Index<IngredientStockGen> indexStockGen = client.initIndex("stock", IngredientStockGen.class);

    public static void main(String[] args) {

        initialiseMapTest();
        cleaningIngredientMap();
        addStocks(ingredientMap);
    }

    private static void initialiseMapTest() {
        ingredientMap.put("potato", 2);
        ingredientMap.put("tomato", 5);
        ingredientMap.put("chicken", 1);
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
        IndexIterable<IngredientUpd> ingredients = indexIngredientUpd.browse(new Query(""));
        List<IngredientUpd> ingredientList = ingredients.stream().collect(Collectors.toList());

        return (ingredientList.contains(new IngredientUpd().setText(key)));

    }

    public static void addIngredients(Set<String> ingredients){
        for (String key : ingredients) {
            if (!ingredientExist(key)) {
                try {
                    indexIngredientGen.addObject(new IngredientGen().setText(key));
                }catch (AlgoliaException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void addStocks(Map<String, Integer> stockMap) {
        Iterator<String> it = stockMap.keySet().iterator();
        List<IngredientStockUpd> ingredientStocks = indexStockUpd.browse(new Query()).stream().collect(Collectors.toList());
        while (it.hasNext()){
            String key = it.next();
            IngredientStockUpd ingredient = new IngredientStockUpd().setText(key);
            try {
                if(ingredientStocks.contains(ingredient)) {
                    int indexIngredient = ingredientStocks.indexOf(ingredient);
                    int actualQuantity = ingredientStocks.get(indexIngredient).getQuantity();
                    IngredientStockUpd ingredientStockUpd = ingredientStocks.get(indexIngredient);
                    ingredientStockUpd.setQuantity(actualQuantity + stockMap.get(key));
                    indexStockUpd.addObject(ingredientStockUpd);
                }else{
                    indexStockGen.addObject(new IngredientStockGen().setText(key).setQuantity(stockMap.get(key)));
                }
            } catch (AlgoliaException e) {
                e.printStackTrace();
            }

        }

    }


    public String getStock() {
        try {
            String string = indexStockUpd.search(new Query("").setAttributesToRetrieve(Arrays.asList("text"))).toString();
            return string;
        } catch (AlgoliaException e) {
            e.printStackTrace();
        }
        return "";
    }

}
