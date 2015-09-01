package pucheril.nathan.MozaicV2;

import java.util.Random;
import java.util.Set;

public class MozaicUtils {
    
    /**
     * Returns a random item out of a set
     * @param set
     * @return
     */
    protected <K> K randomSetElement(Set<K> set) {
        int size = set.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(K elem : set)
        {
            if (i == item) return elem;
            i++;
        }
        System.out.println("UNREACHABLE CODE REACHED!");
        return null;//Should be unreachable
    }

}
