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
     * =====================================单链表结果验证(Start)=============================================
     */

    /**
     * 定义一个单链表节点
     */
    static class Node {
        /**
         * 当前节点的val
         */
        String val;
        /**
         * 当前节点的next节点
         */
        Node next;

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 案例描述：验证单链表
     * <p>
     * - 在使用过程中发现，移除某个元素时，需要先找到当前移除节点的前一个节点和后一个节点，然后需要将前一个节点的next指向后一个节点，此时就完成了元素的移除。
     * 问题：在单链表中，如何确定节点的前一个节点和后一个节点呢？
     * 确定后继节点：找到当前节点的next节点即为后继节点。
     * 确定前一个节点：我想到的方式是定义一个dummy节点指向head,然后移动dummy节点，如果dummy节点的next节点等于当前节点，那么此时dummy节点即为当前节点的前一个节点。
     * </p>
     */
    @Test
    public void testSingleLinkedList() {
        Node node1 = new Node("1", null);
        Node node2 = new Node("2", null);
        Node node3 = new Node("3", null);
        Node node4 = new Node("4", null);

        // 创建单链表(手动创建链表将所有节点连接起来)
        //node1.next = node2;
        //node2.next = node3;
        //node3.next = node4;

        // 创建单链表的头或尾节点，然后移动尾节点，创建单链表
        Node head = node1;
        Node tail = node1;
        // A -> B -> C -> D -> E

        tail.next = node2;
        tail = tail.next;

        tail.next = node3;
        tail = tail.next;

        tail.next = node4;
        tail = tail.next;

        // 比较node3的next节点 和node4节点 作比较
        System.out.println("比较node3的next节点 和node4节点, cmp result:" + (node3.next == node4));

        // TODO 在当前链表尾部插入一个元素
        Node newNode = new Node("5", null);
        tail.next = newNode;
        tail = tail.next;// 移动尾节点
        assertEquals("5", tail.val, "当前tail节点的值期望是5");

        // TODO 在当前链表头部插入一个元素
        Node newHead = new Node("0", null);
        newHead.next = head;
        head = newHead;
        assertEquals("0", head.val, "当前head节点的值期望是0");

        // 查找node4的前置节点node3，即就是前一个节点的node的val为3
        Node preNode3 = findPreNode(head, node4);
        assertEquals("3", preNode3.val, "当前preNode节点的值期望是3");

        Node preNode0 = findPreNode(head, node1);
        assertEquals("0", preNode0.val, "当前preNode节点的值期望是0");

        Node preNodeHeadIsNull = findPreNode(head, newHead);
        assertEquals(null, preNodeHeadIsNull, "当前preNode节点的值期望是null");

        Node preNodeTail5 = findPreNode(head, newNode);
        assertEquals("4", preNodeTail5.val, "当前preNode节点的值期望是4");

        //Node preNodeTailIsNotExist = findPreNode(head, new Node("6", null));

        // 遍历单链表
        printNode(head);
    }

    /**
     * 找到当前节点的前一个节点
     *
     * @param currnetNode
     * @return
     */
    private Node findPreNode(Node head, Node currnetNode) {
        if (head == null || head.next == null) {
            // 链表为空或者链表只有一个节点
            return null;
        }

        if (head == currnetNode) {
            // 如果移除的是头节点 那么前置节点为null
            return null;
        }

        // 创建一个dummy节点指向head
        Node dummy = head;
        while (dummy.next != null) {
            // 比较当前节点的引用而非值
            if (dummy.next == currnetNode) {
                return dummy;
            }
            dummy = dummy.next;// 移动dummy节点 指向下一个
        }

        // 此时表示当前节点未找到前置节点
        throw new RuntimeException("当前节点不在链表中");
    }

    /**
     * 遍历单链表
     *
     * @param node
     */
    private void printNode(Node node) {
        // 遍历单链表
        for (Node dummyNode = node; dummyNode != null; dummyNode = dummyNode.next) {
            System.out.println(dummyNode.val);
        }
    }

    /**
     * =====================================单链表结果验证(End)=============================================
     */

    /**
     * 案例描述：双向链表操作
     */
    @Test
    public void testDoubleLinkedList() {
        System.out.println("双向链表操作");
    }
}
