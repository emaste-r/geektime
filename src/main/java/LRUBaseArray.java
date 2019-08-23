import java.util.HashMap;

/**
 * Created by ouyang on 2019/8/15
 * 思路和链表差不多，就是不断往后移动罢了。
 * 每一次add操作都是先查找，O(N)
 * 找到了后挪动也是O(N)
 * 空间复杂度也是，O(N)
 */

public class LRUBaseArray<T> {
    private Integer capacity;
    private Integer length;
    private final static Integer DEFAULT_CAPACITY = 5;

    private T[] value;
    private HashMap<T, Integer> holder;

    public static void main(String[] args) {
        System.out.println("hello world!");
        LRUBaseArray<Integer> arr = new LRUBaseArray<>();
        arr.print();

        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(7);

        arr.print();

        System.out.println("找到位置：" + arr.findIndex(7));
        System.out.println("找到位置：" + arr.findIndex(1));

        arr.print();
    }

    private LRUBaseArray() {
        length = 0;
        capacity = DEFAULT_CAPACITY;
        value = (T[]) new Object[capacity + 1];
        holder = new HashMap<>(16);
    }

    private void print() {
        for (int i = 0; i < length; i++) {
            System.out.println(value[i].toString());
        }
    }

    /**
     * 寻找节点的下标
     *
     * @param node 待寻找的节点
     * @return
     */
    private Integer findIndex(T node) {
        Integer index = holder.getOrDefault(node, -1);
        if (index == -1) {
            add(node);
            index = 0;
        }
        return index;
    }

    /**
     * 从index开始，往后挪动一位
     *
     * @param start 下标 - 开始
     * @param end   下标 - 结束
     */
    private void shirtRight(Integer start, Integer end) {
        for (int i = end; i > start; i--) {
            value[i] = value[i - 1];
            holder.put(value[i], i);
        }
        // 最后一个要从hash表删除掉
        holder.remove(value[end]);
    }

    private void add(T node) {
        // 首先要知道这个node在第几个index
        Integer index = holder.getOrDefault(node, -1);

        // 它本来就在头部的，啥都不需要做
        if (index == 0) {
            return;
        }


        // 如果没找到，则插入到最前，如果满了则删除最后的
        if (index == -1) {
            shirtRight(0, length);
            value[0] = node;
            if (length < capacity) {
                length++;
            }
        } else {
            // 如果找到了，挪到最前，其他的依次往后移动一位
            shirtRight(0, index);
            value[0] = node;
        }

        // 更新node的位置
        holder.put(node, 0);

    }


}
