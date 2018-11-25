package com.hackrecipe.billscanning.model;

import java.util.Objects;

public class IngredientGen {
	private String text;

	public IngredientGen() {}
	
	public String getText() {
	  return text;
	}
	
	public IngredientGen setText(String text) {
	  this.text = text;
	  return this;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientGen that = (IngredientGen) o;
        return text.equalsIgnoreCase(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }


	@Override
	public String toString() {
		return text;
	}
}

