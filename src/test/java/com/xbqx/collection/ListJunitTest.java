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
     * 定义一个双向链表节点
     */
    static class ListNode {
        ListNode pre;
        String val;
        ListNode next;

        public ListNode(ListNode pre, String val, ListNode next) {
            this.pre = pre;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 案例描述：双向链表操作
     */
    @Test
    public void testDoubleLinkedList() {
        ListNode node1 = new ListNode(null, "A", null);
        ListNode node2 = new ListNode(null, "B", null);
        ListNode node3 = new ListNode(null, "C", null);
        ListNode node4 = new ListNode(null, "D", null);

        // (手动创建链表将所有节点连接起来)
        //// A -> B && B -> A
        //node1.next = node2;
        //node2.pre = node1;
        //// B -> C && C -> B
        //node2.next = node3;
        //node3.pre = node2;
        //// C -> D && D -> C
        //node3.next = node4;
        //node4.pre = node3;

        // 创建一个head和tail节点,将上述节点连接起来
        ListNode head = node1;
        ListNode tail = node1;

        tail.next = node2;
        node2.pre = tail;
        tail = node2;// 移动尾节点

        tail.next = node3;
        node3.pre = tail;
        tail = node3;

        tail.next = node4;
        node4.pre = tail;
        tail = node4;

        // 尾插法：添加一个节点 E
        ListNode nodeE = new ListNode(null, "E", null);
        tail.next = nodeE;
        nodeE.pre = tail;
        tail = nodeE;
        assertEquals("E", tail.val, "当前tail节点的值期望是E");

        // 替换node A
        ListNode nodeF = new ListNode(null, "F", null);
        // A->B
        ListNode next = node1.next;
        head = nodeF;
        nodeF.next = next;
        next.pre = nodeF;
        node1.next = null;

        // 替换node C
        //replaceMiddleNodeC(node3);

        // 替换node E
        ListNode nodeG = new ListNode(null, "G", null);
        // E的前一个节点 D
        ListNode pre = tail.pre;
        pre.next = nodeG;
        nodeG.pre = pre;
        tail = nodeG;
        assertEquals("G", tail.val, "当前tail节点的值期望是G");

        // --从前往后遍历
        printListNodeByHead(head);
        // --从后往前遍历
        printListNodeByTail(tail);
    }

    private void replaceHeadNodeA(ListNode nodeA) {

    }

    /**
     * 替换中间节点C
     *
     * @param nodeC
     */
    private void replaceMiddleNodeC(ListNode nodeC) {
        // 中间节点C 插入一个节点 F
        ListNode nodeF = new ListNode(null, "F", null);
        // A -> B -> C -> D -> E
        // next代表C的下一个节点，也就是D
        ListNode next = nodeC.next;
        // C的pre节点，也就是B
        ListNode pre = nodeC.pre;
        // B -> F
        pre.next = nodeF;
        // F -> B
        nodeF.pre = pre;
        // F -> D
        nodeF.next = next;
        // D -> F
        next.pre = nodeF;

        // TODO 断开链表节点C前后的引用，如果没有断开，虽然上述B<->F,F<->D,已经绕开了节点C,但是节点C的引用还存在，那么C的引用就会造成内存泄漏
        nodeC.pre = null;
        nodeC.next = null;
    }

    /**
     * 从头节点开始遍历
     *
     * @param node
     */
    private void printListNodeByHead(ListNode node) {
        System.out.println("=========从前往后遍历(Start)=========");
        for (ListNode dummyNode = node; dummyNode != null; dummyNode = dummyNode.next) {
            System.out.println(dummyNode.val);
        }
        System.out.println("=========从前往后遍历(End)=========");
    }

    /**
     * 从尾节点开始遍历
     *
     * @param node
     */
    private void printListNodeByTail(ListNode node) {
        System.out.println("=========从后往前遍历(Start)=========");
        for (ListNode dummyNode = node; dummyNode != null; dummyNode = dummyNode.pre) {
            System.out.println(dummyNode.val);
        }
        System.out.println("=========从后往前遍历(End)=========");
    }
}
