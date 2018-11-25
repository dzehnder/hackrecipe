package com.hackrecipe.billscanning.model;

import java.util.Objects;

public class IngredientStockGen {

	private int quantity;
	private String text;

	public IngredientStockGen() {
	}
	
	public IngredientStockGen addQuantity(int quantityToAdd)
	{
		this.setQuantity(this.getQuantity() + quantityToAdd);
		return this;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public IngredientStockGen setQuantity(int quantity)
	{
		this.quantity = quantity;
		return this;
	}
	
	public String getText() {
	  return text;
	}
	
	public IngredientStockGen setText(String text) {
	  this.text = text;
	  return this;
	}


	@Override
	public String toString() {
		return text;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientStockGen that = (IngredientStockGen) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
