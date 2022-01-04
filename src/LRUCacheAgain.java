import java.util.HashMap;
import java.util.Map;

public class LRUCacheAgain {

    private final int totalCap;
    private int cap;
    private  final KV head;
    private  final KV tail;
    private final Map<Integer,KV> cacheMap;

    public LRUCacheAgain(int capacity) {
        totalCap = capacity;
        head = new KV();
        tail = new KV();
        head.next = tail;
        tail.prev = head;
        cacheMap = new HashMap<>();
    }

    /* Use a Lined list to store the key and value */
    class KV {
        int key;
        int value;
        KV next;
        KV prev;
        public KV() {

        };

        public KV(int k,int v) {
            key = k;
            value = v;
        }
    }

    private void removeFromCache(KV val) {
        KV prev = val.prev;
        KV next = val.next;
        if (prev != null) {
            prev.next = next;
        }
        if(next != null) {
            next.prev = prev;
        }
        val.prev = null;
        val.next = null;
    }
    /*
    Always after HEAD
     */
    private void addToCahce(KV val) {
        KV cStart = head.next;
        cStart.prev = val;
        val.next = cStart;
        val.prev = head;
        head.next = val;
    }


    public int get(int key) {
       KV val =  cacheMap.getOrDefault(key,null);
       if (val == null) return -1;
       removeFromCache(val);//
       addToCahce(val);//always at front.
       return val.value;
    }

    public void put(int k,int v) {
         KV val =  cacheMap.getOrDefault(k,null);
         boolean isNew = false;
        if (val == null) {
            val = new KV(k,v);
            cacheMap.put(k,val);
            isNew = true;
        }
        if (!isNew) {
            removeFromCache(val);
            cap--;
        } else if (cap == totalCap) {
            removeFromCache(tail.prev);
            cacheMap.remove(tail.prev.key);
            cap--;
        }
        addToCahce(val);
        cap++;
    }
}
