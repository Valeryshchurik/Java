package com.epam.shchuryk.infohandling.entity;

import com.epam.shchuryk.infohandling.exception.UnavailableComponentOperation;

public class Leaf implements Component {
    private String value;
    private LeafType type;

    public Leaf(String value, LeafType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public LeafType getType() {
        return type;
    }

    @Override
    public void add(Component component) throws UnavailableComponentOperation {
        throw new UnavailableComponentOperation("Add operation cannot be applied to type Leaf.");
    }

    @Override
    public void remove(Component component) throws UnavailableComponentOperation {
        throw new UnavailableComponentOperation("Remove operation cannot be applied to type Leaf.");
    }

    @Override
    public Component getElement(int index) {
        return this;
    }

    @Override
    public int countSizeContext() {
        return 0;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Leaf)) {
            return false;
        }

        Leaf leaf = (Leaf) object;

        return (value != null ? value.equalsIgnoreCase(leaf.value) : leaf.value == null) && type == leaf.type;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.toLowerCase().hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
