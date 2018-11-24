package com.hackrecipe.billscanning.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.algolia.search.*;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.Query;
import com.hackrecipe.billscanning.model.Ingredient;
import com.hackrecipe.billscanning.model.IngredientStock;
import org.springframework.stereotype.Service;

@Service
public class DB_Service {

	private static Map<String, Integer> ingredientMap = new HashMap<String, Integer>();
	private static APIClient client =  new ApacheAPIClientBuilder("ZV6V83N86C", "dcb2605958712d4d75acf4b30c6896d3").build();
	private static Index<Ingredient> indexIngredient = client.initIndex("ingredients", Ingredient.class);
	private static Index<IngredientStock> indexStock = client.initIndex("stock", IngredientStock.class);
	
	public static void main(String[] args) {
		
		initialiseMapTest();
		cleaningIngredientMap();
		updateStock();
		
		try {
			System.out.println(indexIngredient.search(new Query("chicken").setAttributesToRetrieve(Arrays.asList("text"))).getHits().get(0).getObjectID());

		} catch (AlgoliaException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	private static void initialiseMapTest(){
		ingredientMap.put("potato", 2);
		ingredientMap.put("tomato", 5);
		ingredientMap.put("chicken", 1);
		ingredientMap.put("topinambour", 2);
	}
	
	private static void cleaningIngredientMap(){
		Iterator<String> it = ingredientMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			try {
				if (indexIngredient.search(new Query(key).setAttributesToRetrieve(Arrays.asList("text"))).getHits().size() == 0){ 
					it.remove();  
					System.out.println("Removed from map: "+key); 
				}
			} catch (AlgoliaException e) {
				e.printStackTrace();
			}
		}
	}

	public String getStock(){
	    try {
           String string = indexStock.search(new Query("").setAttributesToRetrieve(Arrays.asList("text"))).toString();
           return string;
        }catch (AlgoliaException e){
	        e.printStackTrace();
        }
        return "";
    }
	
	private static void updateStock(){

		
		Iterator<String> it = ingredientMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String id = "";
			try {
				if((id = indexIngredient.search(new Query(key).setAttributesToRetrieve(Arrays.asList("text"))).getHits().get(0).getObjectID()) != null){
					indexStock.addObject(indexStock.getObject(id).get().addQuantity(ingredientMap.get(key))); 
				}else {
					indexStock.addObject(new IngredientStock().setText(key).setQuantity(ingredientMap.get(key))); 
				}
			} catch (AlgoliaException e) {
				e.printStackTrace();
			}
		}
	}

}
