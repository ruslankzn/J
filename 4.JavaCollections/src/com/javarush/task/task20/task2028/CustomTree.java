//////package com.javarush.task.task20.task2028;
//////
//////import java.io.Serializable;
//////import java.util.*;
//////
//////
///////*
//////Построй дерево(1)
//////*/
//////public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
//////    Entry<String> root ;
//////
//////    private ArrayList<Entry> treeArray = new ArrayList<>();
//////
//////
//////    public CustomTree() {
//////        this.root = new Entry<>("0");
//////        treeArray.add(0, root);
//////    }
//////
//////        @Override
//////    public String get(int index) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    public String set(int index, String element) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    public void add(int index, String element) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    public String remove(int index) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    public List<String> subList(int fromIndex, int toIndex) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    public boolean addAll(int index, Collection<? extends String> c) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    @Override
//////    protected void removeRange(int fromIndex, int toIndex) {
//////        throw  new UnsupportedOperationException();
//////    }
//////
//////    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
//////    static class Entry<T> implements Serializable {
//////        protected String elementName;
//////        protected int lineNumber;
//////        protected boolean availableToAddLeftChildren, availableToAddRightChildren;
//////        protected Entry<T> parent, leftChild, rightChild;
//////
//////        public Entry(String elementName) {
//////            this.elementName = elementName;
//////            this.availableToAddLeftChildren = true;
//////            this.availableToAddRightChildren = true;
//////        }
//////
//////        void checkChildren(){
//////            if(leftChild != null)
//////            this.availableToAddLeftChildren = false;
//////            if(rightChild != null)
//////                this.availableToAddRightChildren = false;
//////        }
//////
//////        protected boolean isAvailableToAddChildren(){
//////            if (availableToAddLeftChildren || availableToAddRightChildren) return true;
//////            else return false;
//////        }
//////    }
//////    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
//////
//////    @Override
//////    public boolean add(String elementName) {
//////        Entry current;
//////        Entry newEntry = new Entry(elementName);
//////        for(int i = 0; i < treeArray.size(); i ++){
//////            current = treeArray.get(i);
//////            if(current.isAvailableToAddChildren()){
//////                if(current.leftChild == null){
//////                    current.leftChild = newEntry;
//////                }
//////                else
//////                    current.rightChild = newEntry;
//////
//////                newEntry.parent = current;
//////                current.checkChildren();
//////                treeArray.add(newEntry);
//////                return true;
//////            }
//////        }
//////        return false;
//////    }
//////
//////
//////    @Override
//////    public int size() {
//////        int count=0;
//////        for(Entry e: this.treeArray) {
//////            if (e!=null) {
//////                count++;
//////            }
//////        }
//////        return count-1;
//////    }
//////
//////    public String getParent(String s) {
//////        for (Entry<String> item : treeArray) {
//////            if(item.elementName.equals(s))
//////                return item.parent.elementName;
//////        }
//////        return null;
//////    }
//////
//////
//////    @Override
//////    public boolean remove(Object o) {
//////        if(!(o instanceof String))
//////            throw new UnsupportedOperationException();
//////
//////        for(int i = 0; i < treeArray.size(); i ++){
//////            if(treeArray.get(i).elementName.equals(o)){
//////                recursiveRemove(i);
//////                break;
//////            }
//////        }
//////        {
//////            int maxIndex = -1;
//////            for(int i = 0; i < treeArray.size(); i ++){
//////                if(!(treeArray.get(i).elementName.equals("deleted")))
//////                    maxIndex = i;
//////            }
//////            for(int i = treeArray.size() - 1; i != maxIndex; i --){
//////                treeArray.remove(i);
//////            }
//////        }
//////        return false;
//////    }
//////
//////    private void recursiveRemove(int root){
//////        if(root < treeArray.size()){
//////            recursiveRemove((root * 2) + 1);
//////            recursiveRemove((root * 2) + 2);
//////
//////            treeArray.set(root, new Entry("deleted"));
//////            Entry parent = treeArray.get((root - 1) / 2);
//////
//////            if(root % 2 == 1)
//////                parent.leftChild = null;
//////            else
//////                parent.rightChild = null;
//////        }
//////    }
//////}
////
////package com.javarush.task.task20.task2028;
////
////import java.io.Serializable;
////import java.util.AbstractList;
////import java.util.ArrayList;
////import java.util.Collection;
////import java.util.List;
////
/////*
////Построй дерево(1)
////*/
////public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
////
////    Entry<String> root;
////    List<Entry<String>> list2 = new ArrayList<>();
////
////    public CustomTree() {
////        root = new Entry<>("0");
////        list2.add(root);
////    }
////
////    @Override
////    public String get(int index) {
////        throw new UnsupportedOperationException();
////        //return null;
////    }
////
////    @Override
//////    public int size() {
//////        int size = 0;
//////        for (int i = 0; i < list2.size(); i++) {
//////            if (!(list2.get(i).elementName.equals("deleted")))
//////                size++;
//////        }
//////
//////        return size;
//////    }
////
////    public int size() {
////        int count=0;
////        for(Entry e: this.list2) {
////            if (e!=null) {
////                count++;
////            }
////        }
////        return count-1;
////    }
////
////
////    @Override
////    public String set(int index, String element) {
////        throw new UnsupportedOperationException();
////        //return super.set(index, element);
////    }
////
////    @Override
////    public void add(int index, String element) {
////        throw new UnsupportedOperationException();
////        //super.add(index, element);
////    }
////
////    @Override
////    public boolean add(String s) {
////        Entry<String> child = new Entry<String>(s);
////        int newRec = list2.size();  // new Child
////
////        // find exist Parent
////        while ((list2.get((newRec - 1) / 2).elementName.equals("deleted")) && (newRec < 100)) {
////            list2.add(new Entry<>("deleted"));
////            newRec++;
////        }
////
////        //add links Parent<>Child
////        int parentIndex = (newRec - 1) / 2;
////
////        Entry<String> parent = list2.get(parentIndex);
////        if (parent.leftChild == null)
////            parent.leftChild = child;
////        else parent.rightChild = child;
////        list2.set(parentIndex, parent);
////
////        // add Child to Array
////        if (newRec == list2.size())
////            list2.add(child);
////
////        return true;
////    }
////
////    public String getParent(String s) {
////        for (int i = 0; i < list2.size(); i++) {
////            if (list2.get(i).elementName.equals(s)) {
////                return list2.get((i - 1) / 2).elementName;
////            }
////        }
////        return null;
////    }
////
////    @Override
////    public String remove(int index) {
////        throw new UnsupportedOperationException();
////
////        //return super.remove(index);
////    }
////
////    @Override
////    public boolean remove(Object o) {
//////        System.out.println("!");
////        if (!(o instanceof String)) throw new UnsupportedOperationException();
////        for (int i = 0; i < list2.size(); i++) {
////            if (list2.get(i).elementName.equals(o)) {
////                recRemove(i);       // Recursion
////                break;
////            }
////        }
////        {
////            // trim Array
////            int maxIndex = -1;
////            for (int i = 0; i < list2.size(); i++) {
////                if (!(list2.get(i).elementName.equals("deleted")))
////                    maxIndex = i;
////            }
////            for (int i = list2.size()-1; i != maxIndex ; i--) {
////                list2.remove(i);
////            }
////        }
////
////        return false;
////    }
////
////    private void recRemove(int root) {
////        // System.out.println("recRemove  " + root);
////        if (root < list2.size()) {
////
////            recRemove((root * 2) + 1);
////            recRemove((root * 2) + 2);
////
////            list2.set(root, new Entry<>("deleted"));
////            Entry parent = list2.get((root - 1) / 2);
////
////            if (root % 2 == 1) parent.leftChild = null;
////            else parent.rightChild = null;
////        }
////    }
////
////    @Override
////    public List<String> subList(int fromIndex, int toIndex) {
////        throw new UnsupportedOperationException();
////        //return super.subList(fromIndex, toIndex);
////    }
////
////    @Override
////    protected void removeRange(int fromIndex, int toIndex) {
////        throw new UnsupportedOperationException();
////        //super.removeRange(fromIndex, toIndex);
////    }
////
////    @Override
////    public boolean addAll(int index, Collection<? extends String> c) {
////        throw new UnsupportedOperationException();
////        //return super.addAll(index, c);
////    }
////
////    static class Entry<T> implements Serializable {
////        Entry<T> root;
////        String elementName;
////        int lineNumber;
////        boolean availableToAddLeftChildren, availableToAddRightChildren;
////        Entry<T> parent, leftChild, rightChild;
////
////        public Entry(String elementName) {
////            this.elementName = elementName;
////            availableToAddLeftChildren = true;
////            availableToAddRightChildren = true;
////        }
////
////        void checkChildren() {
////            if (leftChild != null) availableToAddLeftChildren = false;
////            if (rightChild != null) availableToAddRightChildren = false;
////        }
////
////        public boolean isAvailableToAddChildren() {
////            return availableToAddLeftChildren || availableToAddRightChildren;
////        }
////    }
////}
//
//package com.javarush.task.task20.task2028;
//
//import java.io.Serializable;
//import java.util.*;
//
///*
//Построй дерево(1)
//*/
//public class CustomTree extends AbstractList<String>
//        implements List<String>, Cloneable, Serializable {
//    int size = 0;
//    Node<String> root = new Node<>("0", null);
//    Node<String> parent = root;
//
////    public static void main(String[] args) {
////        List<String> list = new CustomTree();
////        for (int i = 1; i < 16; i++) {
////            list.add(String.valueOf(i));
////        }
////        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
////        list.remove("5");
////        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
////    }
//
//    public Node<String> findNode(String value) {
//        Queue<Node<String>> wholeTree = getSubTree(root);
//        for (Node<String> node : wholeTree) {
//            if (node.value == null) {
//                return null;
//            }
//            if (node.value.equals(value)) {
//                return node;
//            }
//        }
//        return null;
//    }
//
//    public Queue<Node<String>> getSubTree(Node<String> top) {
//        Queue<Node<String>> queue = new LinkedList<>();
//        Queue<Node<String>> subTree = new LinkedList<>();
//        if (top != root) {
//            subTree.add(top);
//        }
//        do {
//            if (top.left != null) {
//                queue.add(top.left);
//            }
//            if (top.right != null) {
//                queue.add(top.right);
//            }
//            if (!queue.isEmpty()) {
//                top = queue.poll();
//            }
//            if (!subTree.contains(top)) {
//                subTree.add(top);
//            }
//        }
//        while (!queue.isEmpty());
//        return subTree;
//    }
//
//    @Override
//    public boolean remove(Object value) {
//        if (!(value instanceof String)) throw new UnsupportedOperationException();
//        Queue<Node<String>> queueForRemove = getSubTree(findNode((String) value));
//        parent = findNode((String) value).parent;
//        for (Node<String> node : queueForRemove) {
//            if (node.parent != null && node.left != null) {
//                node.parent.left = null;
//            } else if (node.parent != null) {
//                node.parent.right = null;
//            }
//            size--;
//        }
//        return true;
//    }
//
//    public String getParent(String value) {
//        Node<String> searchingNode = findNode(value);
//        if (searchingNode == null) {
//            return null;
//        }
//        return (searchingNode.parent == null) ? null : searchingNode.parent.value;
//    }
//
//    @Override
//    public boolean add(String value) {
//        Node<String> newNode = new Node<>(value, parent);
//        if (parent.left == null) {
//            newNode.parent = parent;
//            parent.left = newNode;
//            size++;
//        } else if (parent.right == null) {
//            newNode.parent = parent;
//            parent.right = newNode;
//            size++;
//        } else {
//            Queue<Node<String>> wholeTree = getSubTree(root);
//            for (Node<String> node : wholeTree) {
//                if (node.left == null || node.right == null) {
//                    parent = node;
//                    break;
//                }
//            }
//            add(value);
//        }
//        return true;
//    }
//
//    private static class Node<String> {
//        String value;
//        Node<String> parent, left, right;
//
//        private Node(String value, Node<String> parent) {
//            this.value = value;
//            this.parent = parent;
//        }
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public String get(int index) {
//        throw new UnsupportedOperationException();
//    }
//}

package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList implements Cloneable, Serializable {
    Entry<String> root;
    static int SIZE = 0;

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String str) {
            elementName = str;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        public Entry findEntry(String s) {
            ArrayDeque<Entry> deque = new ArrayDeque<>();
            Entry x = this;
            deque.add(x);
            do {
                if (!deque.isEmpty()) x = deque.poll();
                if (x.elementName.equals(s)) return x;
                if (x.leftChild != null) deque.add(x.leftChild);
                if (x.rightChild != null) deque.add(x.rightChild);
            } while (!deque.isEmpty());
            return null;
        }

        public int size() {
            ArrayDeque<Entry> deque = new ArrayDeque<>();
            Entry x = this;
            int result = 0;
            deque.add(x);
            do {
                if (!deque.isEmpty()) x = deque.poll();
                if (x.leftChild != null) {
                    deque.add(x.leftChild);
                    result += 1;
                }
                if (x.rightChild != null) {
                    deque.add(x.rightChild);
                    result += 1;
                }
            } while (!deque.isEmpty());
            return result;
        }
    }

    public CustomTree() {
        root = new Entry<>("root");
    }





    public boolean add(String s) {
        Entry x = root;
        if (notAvailableToAdd(x)) {
            makeAvailableToAdd(x);
        }
        boolean added = false;
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild != null) deque.add(x.leftChild);
            else if (x.availableToAddLeftChildren) {
                x.leftChild = new Entry(s);
                x.leftChild.parent = x;
                x.checkChildren();
                added = true;
                break;
            }
            if (x.rightChild != null) deque.add(x.rightChild);
            else if (x.availableToAddRightChildren) {
                x.rightChild = new Entry(s);
                x.rightChild.parent = x;
                x.checkChildren();
                added = true;
                break;
            }
        } while (!deque.isEmpty());
        return true;
    }

    public boolean notAvailableToAdd(Entry x){
        boolean result = true;
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild != null) deque.add(x.leftChild);
            else if (x.availableToAddLeftChildren) {
                result = false;
            }
            if (x.rightChild != null) deque.add(x.rightChild);
            else if (x.availableToAddRightChildren) {
                result = false;
            }
        } while (!deque.isEmpty());
        return result;
    }

    public void makeAvailableToAdd(Entry entry){
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        Entry x = root;
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild == null) x.availableToAddLeftChildren = true;
            else deque.add(x.leftChild);
            if (x.rightChild == null) x.availableToAddRightChildren = true;
            else deque.add(x.rightChild);
        } while (!deque.isEmpty());
    }

    public boolean remove(Object o) {
        String s = "";
        boolean deleted = false;
        try {
            s = (String) o;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException();
        }
        Entry entry = root.findEntry(s);
        if (entry.parent.leftChild != null && entry.parent.leftChild.equals(entry)) {
            entry.parent.leftChild = null;
            entry.parent.availableToAddLeftChildren = false;
            deleted = true;
        }
        if (entry.parent.rightChild != null && entry.parent.rightChild.equals(entry)) {
            entry.parent.rightChild = null;
            entry.parent.availableToAddRightChildren = false;
            deleted = true;
        }
        return deleted;
    }

    @Override
    public int size() {
        return root.size();
    }

    public String getParent(String s) {
        Entry entry = root.findEntry(s);
        if (entry == null) return null;
        else return entry.parent.elementName;
    }

    @Override
    public void add(int index, Object element) {
        add((String) element);
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }
}
