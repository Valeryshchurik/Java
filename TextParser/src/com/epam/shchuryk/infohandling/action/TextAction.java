package com.epam.shchuryk.infohandling.action;

import com.epam.shchuryk.infohandling.entity.Component;
import com.epam.shchuryk.infohandling.entity.CompositePart;
import com.epam.shchuryk.infohandling.entity.Leaf;
import com.epam.shchuryk.infohandling.entity.LeafType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextAction {
    private static Logger LOGGER = LogManager.getLogger(TextAction.class);
    public static final Comparator<Component> paragraphsSizeComparator = Comparator.comparingDouble(TextAction::countWordsInParagraph);

    public void sortParagraphsByNumberOfWords(CompositePart compositePart) {
        compositePart.getContent().sort(paragraphsSizeComparator);
    }

    public int countWordsInText(CompositePart compositePart) {
        int counter = 0;
        List<Component> paragraphs = compositePart.getContent();
        for (Component paragraph : paragraphs) {
            counter += countWordsInParagraph(paragraph);
        }
        return counter;
    }

    public static int countWordsInParagraph(Component component) {
        int counter = 0;
        CompositePart compositePart = (CompositePart) component;
        List<Component> sentences = compositePart.getContent();
        for (Component sentence : sentences) {
            CompositePart sentenceComposite = (CompositePart) sentence;
            List<Component> sentenceComponents = sentenceComposite.getContent();
            for (Component sentenceComponent : sentenceComponents) {
                Leaf leaf = (Leaf) sentenceComponent;
                if (leaf.getType() == LeafType.WORD)
                    counter++;
            }
        }
        return counter;
    }

    public int countRepeatableLexemes(CompositePart compositePart) {
        List<Component> paragraphs = compositePart.getContent();
        int arraysWithDuplicate = 0;
        for (Component paragraph : paragraphs) {
            List<Component> sentences = ((CompositePart) paragraph).getContent();
            for (Component sentence : sentences) {
                if (hasDuplicate((CompositePart) sentence)) {
                    arraysWithDuplicate++;
                }
            }
        }
        return arraysWithDuplicate;
    }

    private boolean hasDuplicate(CompositePart sentence) {
        Set<Component> componentSet = new HashSet<>(sentence.getContent());
        return componentSet.size() != sentence.getContent().size();
    }
}
