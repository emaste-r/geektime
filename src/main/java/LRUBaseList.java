/**
 * Created by ouyang on 2019/7/29.
 * 1、如果能在链表中找到node，则删了这个node，并在链表头插入node；
 * 2、如果链表中没有node，则判断链表满了没：
 * 2.1、满了：删了最后的节点，并把node插入链表头；
 * 2.2、未满：node插入链表头。
 */
public class LRUBaseList<T> {
    private Node<T> head;
    private Integer capacity;
    private Integer length;
    private final static Integer DEFAULT_CAPACITY = 5;

    private LRUBaseList() {
        capacity = DEFAULT_CAPACITY;
        length = 0;

    }

    public static void main(String[] args) {
        System.out.println("hello world!");

        LRUBaseList<Integer> list = new LRUBaseList<>();
        list.head = new Node<>(0);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        list.printLink();
    }

    /**
     * 打印链表
     */
    private void printLink() {
        Node node = head.getNext();
        while (node != null) {
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    /**
     * 添加LRU节点
     *
     * @param data element
     */
    private void add(T data) {
        Node preNode = findPreNode(data);
        // 如果找不到
        if (preNode == null) {
            if (length < capacity) {
                insertElemAtBegin(data);
            } else {
                insertElemAtBegin(data);
                deleteElemAtEnd();
            }
        } else {
            // 找到直接提到最前
            insertElemAtBegin(data);
            deleteElemAtMid(preNode);
        }

    }


    private Node findPreNode(T data) {
        Node p = head;

        // 空链表
        if (p.getNext() == null) {
            return null;
        }

        while (p.getNext() != null) {
            if (data.equals(p.getNext().getData())) {
                return p;
            }
            p = p.getNext();
        }

        return null;
    }

    /**
     * 插入节点到链表头
     *
     * @param data 新节点数据
     */
    private void insertElemAtBegin(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        length += 1;
    }

    /**
     * 删除链表中的某个节点
     *
     * @param preNode 某个节点的前节点
     */
    private void deleteElemAtMid(Node preNode) {
        preNode.setNext(preNode.getNext().getNext());
        length -= 1;
    }


    /**
     * 删除最后的节点
     */
    private void deleteElemAtEnd() {
        Node p = head;

        // 空链表直接返回
        if (p.getNext() == null) {
            return;
        }

        // 倒数第二个节点
        while (p.getNext().getNext() != null) {
            p = p.getNext();
        }

        // 最后一个节点置为null
        Node tmp = p.getNext();
        p.setNext(null);
        tmp = null;

        length -= 1;
    }
}
