package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        map = new HashMap<>(Math.max((int)(collection.size()/.75f)+1,16));
        addAll(collection);
    }

    @Override
    public boolean add(Object e) {
        int startSize = map.size();
        map.put((E)e,PRESENT);
        if(map.size() > startSize){
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.keySet().remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }


    @Override
    public Object clone() throws InternalError {
        try {
            AmigoSet<E> newSet = (AmigoSet<E>) super.clone();
            newSet.map = (HashMap<E,Object>) map.clone();
            return newSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
////        o.defaultWriteObject();
////        o.writeObject(HashMapReflectionHelper.<Integer>callHiddenMethod(map,"capacity"));
////        o.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
////        o.writeInt(map.keySet().size());
////        for(E o1: map.keySet()) {
////            o.writeObject(o1);
////        }
//        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
//        int buckets = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
//        try {
//            o.defaultWriteObject();
//            o.writeInt(buckets);
//            o.writeFloat(loadFactor);
//        } catch (IOException e) {
//
//        }
        s.defaultWriteObject();

        s.writeObject(map.size());
        for(E e:map.keySet()){
            s.writeObject(e);
        }
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
    }

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
//        o.defaultReadObject();
//        int capacity = o.readInt();
//        float loadFactor = o.readFloat();
//        map = new HashMap<>(capacity,loadFactor);
//        while (o.available() > 0) {
//            add(o.readObject());
//        }
//        o.defaultReadObject();
//        try {
//            int bucket = o.readInt();
//            float loadFactor = o.readFloat();
//            HashMap<E, Object> map = new HashMap<E, Object>(bucket, loadFactor);
//        } catch (IOException e) {
//
//        }

        s.defaultReadObject();

        int size = (int) s.readObject();
        Set<E> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add((E) s.readObject());
        }
        int capacity = (int) s.readObject();
        float loadFactor = (float) s.readObject();
        map = new HashMap<>(capacity, loadFactor);
        for (E e : set) {
            map.put(e, PRESENT);
        }
    }
}
