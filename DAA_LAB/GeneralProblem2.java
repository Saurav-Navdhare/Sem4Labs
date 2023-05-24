import java.util.*;

public class GeneralProblem2 {
    public static int find_peak_entry(List<Integer> A) {
        int n = A.size();
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (A.get(mid) < A.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(1);
        arr.add(4);
        int peak_index = find_peak_entry(arr);
        System.out.println("Peak element index: " + peak_index);
    }
}
