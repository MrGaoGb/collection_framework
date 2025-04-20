package com.xbqx.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description 基于链表实现
 * @Author Mr.Gao
 * @Date 2025/4/20 14:37
 */
public class LinkedList<E> implements List<E> {

    private Node<E> head; // 头结点

    private Node<E> tail; // 尾节点

    private int size; // 链表元素的总个数

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        // @1: 定义一个新节点node
        Node<E> newNode = new Node<>(tail, e, null);
        if (tail != null) {
            tail.next = newNode;
        } else {
            // @2: 如果尾节点为空，则表示添加的是第一个元素，那么head节点也要指向新节点
            head = newNode;
        }
        // 尾节点指向新节点
        tail = newNode;
        size++; // 数量加一
        return true;
    }

    @Override
    public boolean add(E e, int index) {
        // @1: 判断index是否合法
        if (index < 0 || index > size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            //@2: 如果index等于size，则添加到尾部，直接调用add方法即可
            return add(e);
        }
        //      A--->C
        //        B
        // @3: 获取index位置的节点Node
        Node<E> indexNode = findNodeByIndex(index);
        Node<E> prev = indexNode.prev;
        Node<E> currentNode = new Node<>(prev, e, indexNode);
        if (prev == null) {
            // @3:如果prev为null，则表示要操作的节点是头节点，那么head节点指向当前节点即可
            head = currentNode;
        } else {
            // @4: 如果prev不为null，则表示要操作的节点不是头节点，那么prev节点指向当前节点即可
            prev.next = currentNode;
        }
        // @5:
        indexNode.prev = currentNode; // 将当前节点的prev指向node
        size++; // 元素个数加一
        return true;
    }

    /**
     * 通过二分查找获取index位置的节点
     *
     * @param index
     * @return
     */
    private Node<E> findNodeByIndex(int index) {
        Node<E> resultNode = null;
        if (index < size / 2) {
            // 从头节点开始遍历
            resultNode = head;
            for (int i = 0; i < index; i++) {
                resultNode = resultNode.next;
            }
        } else {
            // 从尾节点开始遍历
            resultNode = tail;
            for (int i = size - 1; i > index; i++) {
                resultNode = resultNode.prev;
            }
        }
        // 返回node节点
        return resultNode;
    }

    @Override
    public E remove(int index) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }
        // @2:获取index位置的节点Node
        Node<E> indexNode = findNodeByIndex(index);

        // 删除节点Node
        return removeNode(indexNode);
    }

    /**
     * 删除节点Node
     *
     * @param indexNode
     * @return
     */
    private E removeNode(Node<E> indexNode) {
        // A--->B--->C
        Node<E> prev = indexNode.prev;
        Node<E> next = indexNode.next;

        // --头结点判断
        if (prev == null) {
            // @3:如果prev为null，则表示要删除的节点是头节点，那么head节点指向当前节点的下一个节点即可
            head = next;
        } else {
            // @4: 如果prev不为空，则表示要删除的节点不是头节点，那么prev节点指向当前节点的下一个节点即可
            prev.next = next;
        }

        // A--->B--->C
        // --尾节点判断
        if (next == null) {
            // 如果next为空，说明删除的是尾节点，那么tail节点指向当前节点的上一个节点即可
            tail = prev;
        } else {
            next.prev = prev;
        }

        indexNode.prev = null;
        indexNode.next = null; // 当前节点断链，从而方便gc回收
        size--;
        return indexNode.value;// 返回当前节点的value值
    }


    @Override
    public boolean remove(E e) {
        Node<E> indexNode = head;
        // 从头结点开始遍历
        while (indexNode != null) {
            if (indexNode.value.equals(e)) {
                // 如果元素值相等，则删除对应的节点
                removeNode(indexNode);
                return true;
            }
            indexNode = indexNode.next;// 指向下一个节点node
        }
        return false;
    }

    @Override
    public E set(int index, E e) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }

        // 根据下标索引获取当前Node节点
        Node<E> currentNode = findNodeByIndex(index);
        E oldValue = currentNode.value;
        currentNode.value = e; // 设置新值
        return oldValue;
    }

    @Override
    public E get(int index) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }
        return findNodeByIndex(index).value;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }


    /**
     * LinkedList的迭代器实现
     */
    class LinkedListIterator implements Iterator<E> {

        /**
         * 当前节点指向头节点
         */
        private Node<E> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (currentNode == null) {
                // 如果没有元素时，抛出异常：NoSuchElementException
                throw new NoSuchElementException();
            }
            // 返回当前节点的value
            E value = currentNode.value;
            // 将当前节点指向下一个节点
            currentNode = currentNode.next;
            return value;
        }
    }

    /**
     * 定义一个Node节点
     *
     * @param <E>
     */
    class Node<E> {

        E value; // 值
        Node<E> next;// 前一个节点
        Node<E> prev;// 后一个节点

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
