package org.hengxing;

import org.hengxing.model.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    @Test
    public void test01() {
        Person p = new Person();
        p.age = 10;
        System.out.println("p.age = " + p.age);
        p.show();
    }

    @Test
    public void test02() throws Exception {
        //创建实例
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        System.out.println("p = " + p);

        //调用属性
        Field ageField = clazz.getField("age");//通过类来获取属性
        ageField.set(p,12);//设置到对应的对象身上
        System.out.println(ageField.get(p));

        //调用方法
        Method show = clazz.getMethod("show");
        show.invoke(p);
    }

    /**
     * 能做到之前做不到的，
     */
    @Test
    public void test03() throws Exception {
        //调用私有的构造器private Person(String name, int age)
        Class<Person> clazz = Person.class;
        Constructor<Person> cons = clazz.getDeclaredConstructor(String.class, int.class);
        cons.setAccessible(true);//通过这个方法打开私有构造器的访问权限
        Person tom = cons.newInstance("Tom", 12);
        System.out.println(tom);

        //调用私有属性 private String name;
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(tom,"Jerry");
        System.out.println("nameField.get(tom) = " + nameField.get(tom));

        Method method = clazz.getDeclaredMethod("showNation", String.class);
        method.setAccessible(true);
        String nation = (String) method.invoke(tom,"中国");
        System.out.println("nation = " + nation);
    }

    /**
     * 反射的动态性
     */
    @Test
    public void test04() {
        //1.调用运行时类的静态属性class
        Class<Person> p = Person.class;
        System.out.println(p);

        //2.调用对象的getClass()
        Person person = new Person();
        Class p2 = person.getClass();

        System.out.println("p == p2 = " + (p == p2));//p == p2 = true

        //3.调用Class的静态方法forName(String Classname)
    }
}
