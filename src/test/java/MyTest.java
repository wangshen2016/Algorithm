import com.wangxshen.heap.MaxHeap;
import com.wangxshen.sort.BubbleSort;
import com.wangxshen.sort.HeapSort;
import com.wangxshen.sort.quickSort.QuickSort;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 15:30
 * @Version 1.0
 */
public class MyTest {

    @Test
    public void testSort() {
        int[] arr = new int[] {3, 2, 1, 0, 6, 8, 29, 9};
//        BubbleSort.sort(arr);
//        QuickSort.sort(arr);
//        HeapSort.sort(arr);
//        HeapSort.sort2(arr);
        HeapSort.sort3(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testHeap() {
        MaxHeap maxHeap = new MaxHeap();
    }
}
