/**
 * Created by ouyang on 2019/7/29.
 */
public class LRU_List
{
    public static void  main(String[] args){
        System.out.println("hello world!");

        int maxNodeNum = 100;
        Node head = new Node(0);

        insertNode(head, new Node(1));
        insertNode(head, new Node(2));
        insertNode(head, new Node(3));
        insertNode(head, new Node(4));
        insertNode(head, new Node(5));
        insertNode(head, new Node(6));
        insertNode(head, new Node(7));
        //insertNode(head, new Node(8));

        printLink(head);
    }

    public static void printLink(Node head){
        Node p = head;
        while(p != null){
            System.out.println(p.data);
            if (p.next == null){
                break;
            }else{
                p = p.next;
            }

        }
    }

    public static void insertNode(Node head, Node node){
        Node p = head;
        Node q = head;
        boolean findFlag = false;
        int nodeSize = 0;
        int maxSize = 5;
        while  (p.next != null){
            // 如果能在链表中找到，则删除该节点，并插入到head
            if (p.data == node.data){
                p.next = node.next;
                node.next = head.next;
                head.next = node;
                findFlag = true;
                break;
            }
            p = p.next;
            nodeSize +=1;
            if (q.next != null && q.next.next != null){
                q = q.next;

            }

        }
        // 如果找不到链表，则分2个情况判断，链表满了没，如果没满直接插到头，满了插完头后删除最后节点.
        if (!findFlag && (nodeSize < maxSize)){
            node.next = head.next;
            head.next = node;
        }else if (!findFlag && (nodeSize == maxSize)){
            node.next = head.next;
            head.next = node;
            q.next = null;
        }
    }

}


class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}