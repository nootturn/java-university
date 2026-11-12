package edu.course.lab04.chainedStorage;

import edu.course.lab04.projectTask.ProjectTask;
import edu.course.lab04.storage.IssueId;

import java.util.Objects;

public final class ChainedHashTable {
    private static final class Node {
        final IssueId key;
        ProjectTask value;
        Node next;
        Node(IssueId key, ProjectTask value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final Node[] buckets;
    private int size;
    public ChainedHashTable(){
        this(16);
    }
    public ChainedHashTable(int capacity) {
        if (capacity < 0){
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.buckets = new Node[capacity];
    }

    private int index(IssueId key) {
        return Math.floorMod(Objects.requireNonNull(key).hashCode(), buckets.length);
    }

    public ProjectTask put(IssueId key, ProjectTask value){
        Objects.requireNonNull(value);
        int i = index(key);
        for (Node n = buckets[i]; n != null; n = n.next) {
            if (n.key.equals(key)){
                ProjectTask old = n.value;
                n.value = value;
                return old;
            }
        }
        buckets[i] = new Node(key, value, buckets[i]);
        size++;
        return null;
    }

    public ProjectTask get(IssueId key){
        for (Node n = buckets[index(key)]; n != null; n = n.next){
            if (n.key.equals(key)){
                return n.value;
            }
        }
        return null;
    }

    public boolean containsKey(IssueId key){
        return get(key) != null;
    }
    public ProjectTask remove(IssueId key){
        int i = index(key);
        Node previous = null;
        for (Node n = buckets[i]; n != null; n = n.next){
            if (n.key.equals(key)){
                if (previous == null){
                    buckets[i] = n.next;
                } else {
                    previous.next = n.next;
                }
                size--;
                return n.value;
            }
            previous = n;
        }
        return null;
    }

    public int size(){
        return size;
    }
}
