package engine;

import java.util.Comparator;

class ComparatorStringTitle implements Comparator<String>{

    public int compare(String a, String b){
        return a.compareTo(b);
    }

}
