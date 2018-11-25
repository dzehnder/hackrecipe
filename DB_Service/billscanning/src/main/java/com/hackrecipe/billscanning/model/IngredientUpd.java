package com.hackrecipe.billscanning.model;

import java.util.Objects;

public class IngredientUpd {

    private String text;
    private Long ObjectId;

    public Long getObjectId() {
        return ObjectId;
    }

    public void setObjectId(Long objectId) {
        ObjectId = objectId;
    }

    public String getText() {
        return text;
    }

    public IngredientUpd setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientUpd that = (IngredientUpd) o;
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
