package com.hackrecipe.billscanning.model;

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
	
	public Ingredient setObjectID(String objectID) {
	  this.objectID = objectID;
	  return this;
	}

	@Override
	public String toString() {
		return text;
	}
}

