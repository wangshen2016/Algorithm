import com.wangxshen.sort.BubbleSort;
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
        int[] arr = new int[] {8, 3, 2, 1, 0, 8, 29, 3};
//        BubbleSort.sort(arr);
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
