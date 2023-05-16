import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

public class TestList {
    @Test
    public void test01() {
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);

        Collection other = new ArrayList();
        other.add("小李广");
        other.add("扫地僧");
        other.add("尚硅谷");
        System.out.println("other = " + other);

        coll.removeAll(other);
        System.out.println("coll.removeAll(other)之后，coll = " + coll);
        System.out.println("coll.removeAll(other)之后，other = " + other);
    }

    @Test
    public void test02() {
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);

        Collection other = new ArrayList();
        other.add("小李广");
        other.add("扫地僧");
        other.add("尚硅谷");
        System.out.println("other = " + other);

        coll.retainAll(other);
        System.out.println("coll.retainAll(other)之后，coll = " + coll);
        System.out.println("coll.retainAll(other)之后，other = " + other);
    }

    @Test
    public void test03() {
        ArrayList<String> list = new ArrayList<>();
        list.add("nihao");
        list.add("我");
        Object[] array = list.toArray();
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
        System.out.println("list.hashCode() = " + list.hashCode());
    }

    @Test
    public void test04() {
        Collection<Integer> coll = new ArrayList();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        Iterator it = coll.iterator();

//        while (it.hasNext()) {
//            Integer next = (Integer) it.next();
//            if (next % 2 == 0) {
//                it.remove();
//            }
//        }
        coll.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer % 2 == 0) return true;
                return false;
            }
        });
        System.out.println("coll = " + coll);
    }

    @Test
    public void test05() {
        ArrayList list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

    }
}
