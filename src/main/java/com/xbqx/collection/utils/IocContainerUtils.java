package com.xbqx.collection.utils;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Mr.Gao
 * @Date 2025/4/20 23:16
 */
public class IocContainerUtils {

    public List<Class<?>> loadClassByPackagePath(String packagePath) throws Exception {
        List<Class<?>> resovedClassList = new ArrayList<>();
        // 将.替换为/
        String replacePackagePath = packagePath.replace(".", "/");
        URL resource = this.getClass().getClassLoader().getResource(replacePackagePath);
        if (resource == null) {
            throw new RuntimeException("资源路径未找到!");
        }
        Path path = Paths.get(resource.toURI());
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path absolutePath = file.toAbsolutePath();
                if (absolutePath.toString().endsWith(".class")) {
                    String replaceStr = absolutePath.toString().replace(File.separator, ".");
                    int packagePathIndex = replaceStr.indexOf(packagePath);
                    String className = replaceStr.substring(packagePathIndex, replaceStr.length() - ".class".length());
                    System.out.println(className);
                    try {
                        Class<?> aClass = Class.forName(className);
                        resovedClassList.add(aClass);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(className + ":类找不到了!");
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
//        System.out.println(path);
        return resovedClassList;
    }


    public static void main(String[] args) throws Exception {
        String packagePath = "com.xbqx.collection.list";
        IocContainerUtils iocUtils = new IocContainerUtils();
        List<Class<?>> classes = iocUtils.loadClassByPackagePath(packagePath);
        System.out.println("....打印加载成功后的class类");
        classes.forEach(System.out::println);
    }
}
