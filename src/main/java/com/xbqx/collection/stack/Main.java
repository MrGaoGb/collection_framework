package com.xbqx.collection.stack;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @apiNote:
 * @date 2025/4/21 16:48
 */
public class Main {

    public static void main(String[] args) {
        // 创建一个栈
        //UStack<Integer> stack = new UStack<>();

        //// 入栈
        //stack.push(10);
        //stack.push(20);
        //stack.push(30);
        //
        //System.out.println("当前栈的元素:" + stack + ", 当前元素的个数为:" + stack.size());
        //
        //// 查看栈顶元素
        //System.out.println("当前栈顶元素:" + stack.peek());
        //
        //// 出栈
        //while (!stack.isEmpty()) {
        //    System.out.println(stack.pop());
        //}
        //
        //// 栈中元素为空，再pop移除一个元素,抛出异常NoSuchElementException
        //System.out.println(stack.pop());
        //
        //System.out.println("当前栈的元素个数:" + stack.size());


        // 模拟多数据源切换,导致数据源被移除或覆盖问题，通过结合栈的先进后出的特性，解决这一问题
        UStack<String> stack = new UStack<>();
        // @1 数据源1入栈
        stack.push("userDataSource");
        // @2 数据源2入栈
        stack.push("baseDataSource");
        try {
            // 休眠2秒 模拟业务执行
            System.out.println("当前栈的元素:" + stack);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // @3 数据源2执行完毕，出栈
        stack.pop();
        try {
            // 休眠2秒 模拟业务执行
            System.out.println("当前栈的元素:" + stack);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // @4 数据源1执行完毕，出栈
        stack.pop();
        System.out.println("当前栈的元素:" + stack);
    }
}
