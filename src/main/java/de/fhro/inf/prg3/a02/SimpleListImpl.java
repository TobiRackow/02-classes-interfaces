package de.fhro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList,Iterable {
    Element head;
    private int size;

    public SimpleListImpl() {
        head = null;
    }

    public Element getHead() {
        return head;
    }

    public void setHead(Element head) {
        this.head = head;
    }

    @Override
    public void add(Object o) {
        if (head == null){
            head = new Element(o);
        }else{
            Element current = head;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(new Element(o));
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for(Object o : this){
            if(filter.include(o)){
                result.add(o);
            }
        }

        return result;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }

    private static class Element{
        Object item;
        Element next;

        public Element(Object item) {
            this.item = item;
            this.next = null;
        }

        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getNext() {
            return next;
        }
    }

    private class SimpleIteratorImpl implements Iterator{

        private Element current = head;
        public SimpleIteratorImpl() {
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }
}
