package com.xbqx.collection;

import com.xbqx.collection.list.ArrayList;
import com.xbqx.collection.list.LinkedList;
import com.xbqx.collection.list.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class ListJunitTest {

    @Test
    public void testArrayList() {

        // 创建ArrayList集合
        List<String> list = new ArrayList<>();
        // 添加20个元素(0~19)
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }

        assertEquals(20, list.size());

        // 移除元素
        list.remove("10");
        list.remove("11");

        // 移除下标为3的元素
        String remove = list.remove(3);
        assertEquals("3", remove);

        // 移除元素后的个数
        assertEquals(17, list.size());

        assertEquals("4", list.get(3));

        assertEquals("19", list.get(16));

        list.forEach(System.out::println);
    }

    /**
     * 基于链表实现
     */
    @Test
    public void testLinkedList() {

        // 创建ArrayList集合
        List<String> list = new LinkedList<>();
        // 添加20个元素(0~19)
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }

        assertEquals(20, list.size());

        // 移除元素
        list.remove("10");
        list.remove("11");

        // 移除下标为3的元素
        String remove = list.remove(3);
        assertEquals("3", remove);

        // 移除元素后的个数
        assertEquals(17, list.size());

        assertEquals("4", list.get(3));

        assertEquals("19", list.get(16));

        list.forEach(System.out::println);
    }

    /**
     * 案例描述：双向链表操作
     */
    @Test
    public void testDoubleLinkedList() {
        System.out.println("双向链表操作");
    }
}
