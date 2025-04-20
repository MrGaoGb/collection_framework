package com.xbqx.collection.list;


/**
 * @Description List集合
 * @Author Mr.Gao
 * @Date 2025/4/19 23:01
 */
public interface List<E> extends Iterable<E> {

    /**
     * 当前元素总个数
     *
     * @return
     */
    int size();

    /**
     * 添加元素
     *
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 指定index位置添加元素
     *
     * @param e
     * @return
     */
    boolean add(E e, int index);

    /**
     * 删除指定元素
     *
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 删除元素
     *
     * @param e
     * @return
     */
    boolean remove(E e);

    /**
     * 修改指定index位置的元素
     *
     * @param index
     * @param e
     * @return
     */
    E set(int index, E e);

    /**
     * 获取指定index位置的元素
     *
     * @param index
     * @return
     */
    E get(int index);
}
