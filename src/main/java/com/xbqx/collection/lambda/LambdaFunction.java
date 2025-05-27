package com.xbqx.collection.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * @author Mr.Gao
 * @apiNote:
 * @date 2025/4/29 11:15
 */
public class LambdaFunction {
    public static void main(String[] args) {
        // lambda表达式

        // 函数式接口：只有一个抽象方法

        // 直接new 实现类
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        // 代码优化
        Function<String, String> function2 = (String s) -> s.toUpperCase();
        // 代码优化 省略参数类型
        Function<String, String> function3 = (s) -> s.toUpperCase();
        // 代码优化 省略参数括号
        Function<String, String> function4 = s -> s.toUpperCase();
        // 结果值
        String result = function.apply("hello");
        String result2 = function2.apply("hello");
        String result3 = function3.apply("hello");
        String result4 = function4.apply("hello");
        System.out.println("结果处理result:" + result + ", result2:" + result2 + ",result3:" + result3 + ", result4:" + result4);
        System.out.println("-----------Function函数(按照String类型处理)---------");
        String paramStr = "Mr.Gao";
        // 利用函数式接口 将String统一处理为小写
        String result5 = processString(paramStr, s -> s.toLowerCase());
        System.out.println("结果处理result5:" + result5);
        // 利用函数式接口 将String统一处理为拼接字符串
        String result6 = processString(paramStr, s -> s + " will go back home!");
        System.out.println("结果处理result6:" + result6);

        // 函数式接口 统一处理函数
        System.out.println("----------Function函数----------");
        String result7 = apply(paramStr, s -> s.toUpperCase());
        System.out.println("结果处理result7:" + result7);
        Integer result8 = apply(100, i -> i * i);
        System.out.println("结果处理result8(num的二次方):" + result8);

        // 函数式接口 BiFunction
        System.out.println("----------BiFunction函数----------");
        BiFunction<Integer, Integer, String> biFunctionToSum = (t, u) -> t + u + "";
        System.out.println("(BiFunction函数), 两个数相加,得到结果值: " + biFunctionToSum.apply(1, 2));
        BiFunction<Integer, Integer, String> biFunctionToMultiply = (t, u) -> t * u + "";
        System.out.println("(BiFunction函数), 两个数相乘,得到结果值: " + biFunctionToMultiply.apply(2, 2));

        // 简化BiFunction
        System.out.println("(BiFunction函数), 两个数相加,得到结果值: " + applyBiFunction(1, 2, (t, u) -> t + u));
        System.out.println("(BiFunction函数), 两个数相乘,得到结果值: " + applyBiFunction(2, 2, (t, u) -> t * u));

        // IntFunction
        System.out.println("----------IntFunction函数----------");
        IntFunction<Integer> intFunction = i -> i * i;
        System.out.println("(IntFunction函数), 获取结果值: " + intFunction.apply(1000));
    }

    /**
     * 将String处理成T(Object类型)
     *
     * @param paramStr
     * @param function
     * @param <T>
     * @return
     */
    public static <T> T processString(String paramStr, Function<String, T> function) {
        T apply = function.apply(paramStr);
        return apply;
    }

    /**
     * 函数式接口 统一处理函数
     *
     * @param key
     * @param function
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> T apply(K key, Function<K, T> function) {
        return function.apply(key);
    }

    /**
     * BiFunction 统一处理函数
     *
     * @param k
     * @param u
     * @param biFunction
     * @param <K>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <K, U, R> R applyBiFunction(K k, U u, BiFunction<K, U, R> biFunction) {
        return biFunction.apply(k, u);
    }
}
