package com.tsystems.javaschool.tasks.calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by veronika on 24.09.2018.
 */
public class CalcStack <T> {
    Deque<T> myList = new LinkedList<>();

    public void push(T value){

        myList.addLast(value);
    }

    public T pop(){
        T result =  myList.pollLast();
            return result;
    }


    public T getLastElement() {
        T result = myList.getLast();
            return result;
    }

    public boolean isEmpty(){
        boolean result = true;
        if (myList.size() != 0){
             result = false;
        }
        return result;
    }

     public void clear(){
       myList.clear();
     }

}
