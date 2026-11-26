package edu.course.lab06;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Stack<T> {
    private final ArrayList<T> stack = new ArrayList<>();

    public void push(T value) {
        stack.add(Objects.requireNonNull(value));
    }

    public T pop(){
        if (isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek(){
        if (isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
