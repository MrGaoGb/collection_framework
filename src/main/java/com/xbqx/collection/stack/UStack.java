package com.xbqx.collection.stack;

import java.util.LinkedList;

/**
 * @author Mr.Gao
 * @apiNote:
 * @date 2025/4/21 16:42
 */
public class UStack<T> {

    /**
     * 链表实现
     */
    private LinkedList<T> list = new LinkedList<>();


    /**
     * 入栈（添加至链表尾部）
     *
     * @param t
     */
    public void push(T t) {
        list.addLast(t);
    }

    /**
     * 出栈(从链表尾部移除)
     *
     * @return
     */
    public T pop() {
        return list.removeLast();
    }

    /**
     * 查看栈顶元素(不删除)
     *
     * @return
     */
    public T peek() {
        return list.getLast();
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 获取栈的大小
     *
     * @return
     */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "UStack{" +
                "list=" + list +
                '}';
    }
}
