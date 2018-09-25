package com.epam.shchuryk.infohandling.entity;

import java.util.ArrayList;
import java.util.List;

public class CompositePart implements Component {
    private List<Component> content = new ArrayList<>();
    private ComponentType type;

    public CompositePart() {}

    public CompositePart(ComponentType type) {
        this.type = type;
    }

    public List<Component> getContent() {
        return content;
    }

    public ComponentType getType() {
        return type;
    }

    @Override
    public int countSizeContext(){return content.size();}

    @Override
    public String toString() {
        StringBuilder originalText = new StringBuilder();
        for (Component component : content) {
            switch (type) {
                case SENTENCE:
                    Leaf leaf = (Leaf) component;
                    switch (leaf.getType()) {
                        case WORD:
                            originalText.append(" ").append(component.toString());
                            break;
                        case PUNCTUATION_MARK:
                            originalText.append(component.toString());
                            break;
                    }
                    break;
                case PARAGRAPH:
                    originalText.append(" ").append(component.toString().trim());
                    break;
                case TEXT:
                    originalText.append("    ").append(component.toString()).append("\n");
                    break;
            }
        }
        return originalText.toString();
    }

    @Override
    public void add(Component component) {
        content.add(component);
    }

    @Override
    public void remove(Component component) {
        content.remove(component);
    }

    @Override
    public Component getElement(int index) {
        return content.get(index);
    }
}
