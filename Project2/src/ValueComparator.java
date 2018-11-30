import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// a comparator that compares Strings
public class ValueComparator implements Comparator<Integer> {

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public ValueComparator(HashMap<Integer, Integer> map){
        this.map.putAll(map);
    }

    @Override
    public int compare(Integer s1, Integer s2) {
        if(map.get(s1) > map.get(s2)){
            return -1;
        }else if(map.get(s1) < map.get(s2)){
            return 1;
        }else
        {
            List<Integer> lists=new ArrayList<Integer>();
            lists.add(1);
            lists.add(-1);
            int index=(int)(Math.random()*lists.size());
            return lists.get(index);
        }
    }
}