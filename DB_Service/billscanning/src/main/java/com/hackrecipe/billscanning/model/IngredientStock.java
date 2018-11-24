package com.hackrecipe.billscanning.model;

public class IngredientStock {

	private int quantity;
	private String text;
	private String objectID;
	
	public IngredientStock() {
	}
	
	public IngredientStock addQuantity(int quantityToAdd)
	{
		this.setQuantity(this.getQuantity() + quantityToAdd);
		return this;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public IngredientStock setQuantity(int quantity)
	{
		this.quantity = quantity;
		return this;
	}
	
	public String getText() {
	  return text;
	}
	
	public IngredientStock setText(String text) {
	  this.text = text;
	  return this;
	}
	
	public String getObjectID() {
	  return objectID;
	}
	
	public IngredientStock setObjectID(String objectID) {
	  this.objectID = objectID;
	  return this;
	}

	@Override
	public String toString() {
		return text;
	}
}
