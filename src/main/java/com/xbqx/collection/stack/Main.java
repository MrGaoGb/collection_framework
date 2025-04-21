package com.xbqx.collection.stack;

/**
 * @author Mr.Gao
 * @apiNote:
 * @date 2025/4/21 16:48
 */
public class Main {

    public static void main(String[] args) {
        // 创建一个栈
        UStack<Integer> stack = new UStack<>();

        // 入栈
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("当前栈的元素:" + stack + ", 当前元素的个数为:" + stack.size());

        // 查看栈顶元素
        System.out.println("当前栈顶元素:" + stack.peek());

        // 出栈
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        // 栈中元素为空，再pop移除一个元素,抛出异常NoSuchElementException
        System.out.println(stack.pop());

        System.out.println("当前栈的元素个数:" + stack.size());
    }
}
