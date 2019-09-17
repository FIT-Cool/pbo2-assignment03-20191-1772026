package com.steven.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Category implements Serializable {
    private final IntegerProperty id=new SimpleIntegerProperty();

    public String getId() {
        return Integer.toString(id.get());
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private final StringProperty name=new SimpleStringProperty();

    @Override
    public String toString() {
        return name.get();
    }
}
