package sort;

/**
 * Created by ouyang on 2019/8/23.
 * <p>
 * 划分2个区：排完序的区域 + 未排序的区域
 */
public class InsertSort {

    public static void main(String[] args) {
        new InsertSort().sort(new int[]{6, 3, 12, 4, 51, 1});
    }

    private void print(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }

    private void sort(int[] arr) {
        System.out.println("初始值...");
        print(arr);
        System.out.println("#########");

        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i-1;

            for (; j >= 0; j--) {
                if (value < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = value;
            print(arr);
        }
        System.out.println("最后的结果...");
        print(arr);
    }
}
