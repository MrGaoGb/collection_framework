package com.xbqx.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description 基于数组实现
 * @Author Mr.Gao
 * @Date 2025/4/19 23:18
 */
public class ArrayList<E> implements List<E> {

    /**
     * 定义一个数组，容量为10
     */
    private Object[] elementData = new Object[10];

    /**
     * 记录元素的总个数
     */
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        // 判断数组是否够用，不够用就扩容
        if (size == elementData.length) {
            resize();// 数组扩容
        }
        elementData[size++] = e;
        return true;
    }

    /**
     * 数组扩容为原来的2倍
     */
    private void resize() {
        // 数组容量定义为原来的2倍
        Object[] newElementData = new Object[elementData.length * 2];
        // 将旧数组的元素拷贝到新数组中
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        // 将新数组赋值给elementData
        this.elementData = newElementData;
    }

    @Override
    public boolean add(E e, int index) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }
        // @2: 判断数组是否够用，不够用就扩容
        if (size == elementData.length) {
            resize();// 数组扩容
        }
        // @3: 循环将index位置后面的元素往后移动一位
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        return true;
    }

    /**
     * 删除某个下标index位置的元素，并返回被删除的元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }

        // @2:获取当前元素节点的oldValue
        E oldValue = (E) elementData[index];

        // @3: 将index位置后面的元素往前移动一位
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

        // @4: 将最后一个元素置为null
        elementData[size - 1] = null; // 方便gc回收
        size--;
        return oldValue;
    }

    /**
     * 删除某个元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean remove(E e) {
        // 循环遍历
        for (int i = 0; i < size; i++) {
            // 如果value值相等，则删除
            if (elementData[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }
        if (elementData[index] != null) {
            // 如果原来index位置的值不为空时，则替换原来的值，返回旧值
            E oldValue = (E) elementData[index];
            elementData[index] = e;
            return oldValue;
        }
        return null;
    }

    @Override
    public E get(int index) {
        // @1: 判断index是否合法
        if (index < 0 || index >= size) {
            // index越界异常
            throw new IndexOutOfBoundsException();
        }

        // @2: 返回index位置的元素
        if (elementData[index] != null) {
            return (E) elementData[index];
        }

        // @3: 如果index位置的值为null，则返回null
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * 定义ArrayList的迭代器实现
     */
    class ArrayListIterator implements Iterator<E> {

        private int cursor; // 游标，记录当前迭代到的位置

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            // 获取当前游标位置的元素，从0开始
            E value = (E) elementData[cursor];
            if (value == null) {
                // 如果当前元素为null，则抛出异常
                throw new NoSuchElementException();
            }
            // 游标后移
            cursor++;
            return value;
        }
    }

}
