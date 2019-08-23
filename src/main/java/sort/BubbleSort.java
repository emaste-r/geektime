package sort;

/**
 * Created by ouyang on 2019/8/23.
 */
public class BubbleSort {

    public static void main(String[] args) {
        new BubbleSort().sort(new int[]{6, 3, 12, 4, 51, 1});
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
        boolean isChange;
        // 次数
        for (int i = 0; i < arr.length; i++) {
            isChange = false;

            // 本轮两两比较决出最强者
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] < arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    isChange = true;
                }
            }
            // 如果本轮没更改，那么就说明已经排完序了..
            if (!isChange) {
                break;
            }
            print(arr);
        }
        System.out.println("最后的结果...");
        print(arr);
    }
}
