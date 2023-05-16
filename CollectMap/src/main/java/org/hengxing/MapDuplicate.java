package org.hengxing;

import org.hengxing.POJO.User;

import java.util.HashMap;

public class MapDuplicate {
    public static void main(String[] args) {
        HashMap<String, User> map = new HashMap<>();
        map.put("coco", new User("hengxing", 234));
        map.put("coco", new User("hengxing", 12));
        System.out.println("map = " + map);
    }

    /*原来是要在这里测试*/
}
