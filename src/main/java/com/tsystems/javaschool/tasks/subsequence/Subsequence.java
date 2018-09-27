package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;
import java.util.ListIterator;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    
    public boolean find(List x, List y) {
        boolean result = false;
        if (x == null || y == null) throw new IllegalArgumentException("There are no elements");
        if (x.isEmpty())
            result = true;
        else {
            ListIterator xIterator = x.listIterator();
            ListIterator yIterator = y.listIterator();
            while (yIterator.hasNext()) {
                if (yIterator.next().equals(x.get(xIterator.nextIndex()))) {
                    xIterator.next();
                }
                if (!xIterator.hasNext()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}



