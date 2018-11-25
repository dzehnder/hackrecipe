package com.hackrecipe.billscanning.model;

import java.util.Objects;

public class Ingredient {
	private String text;
	private String objectID;
	
	public Ingredient() {}
	
	public String getText() {
	  return text;
	}
	
	public Ingredient setText(String text) {
	  this.text = text;
	  return this;
	}
	
	public String getObjectID() {
	  return objectID;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return text.equalsIgnoreCase(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    public Ingredient setObjectID(String objectID) {
	  this.objectID = objectID;
	  return this;
	}

	@Override
	public String toString() {
		return text;
	}
}

