package com.hackrecipe.billscanning.model;

import java.util.Objects;

public class IngredientStockUpd {

    private int quantity;
    private String text;
    private Long ObjectID;

    public int getQuantity() {
        return quantity;
    }

    public IngredientStockUpd setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getText() {
        return text;
    }

    public IngredientStockUpd setText(String text) {
        this.text = text;
        return this;
    }

    public Long getObjectID() {
        return ObjectID;
    }

    public void setObjectID(Long objectID) {
        ObjectID = objectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientStockUpd that = (IngredientStockUpd) o;
        return text.equalsIgnoreCase(that.getText());
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
