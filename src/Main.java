
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class Main {

    public static Map<String,List<Integer>> getPeaks(int[] arr) {

        Map<String,List<Integer>> ans = new HashMap<String,List<Integer>>() {{
            put("pos",   new ArrayList<Integer>() );
            put("peaks", new ArrayList<Integer>() );
        }};
        int posMax = 0;
        boolean matchAsc = false;

        for (int i = 1 ; i < arr.length ; i++) {
            if (arr[i-1] < arr[i]) {
                matchAsc = true;
                posMax = i;
            }
            if (matchAsc && arr[i-1] > arr[i]) {
                matchAsc = false;
                ans.get("pos").add(posMax);
                ans.get("peaks").add(arr[posMax]);
            }
        }
        return ans;
    }
}