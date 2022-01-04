import java.util.HashMap;
import java.util.Map;

/*
146. LRU Cache
Medium

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */
public class LRUCache {
    private final int totalCap;
    private int cap;
    private  final KV head;
    private  final KV tail;
    private final Map<Integer,KV> cacheMap;

    public LRUCache(int capacity) {
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
        //printList();
        KV val =  cacheMap.getOrDefault(key,null);
        if (val == null) return -1;
        removeFromCache(val);//
        addToCahce(val);//always at front.
        printList();
        return val.value;
    }

    public void put(int k,int v) {
        //printList();
        KV val =  cacheMap.getOrDefault(k,null);
        boolean isNew = false;
        if (val == null) {
            val = new KV(k,v);
            cacheMap.put(k,val);
            isNew = true;
        }
        if (!isNew) {
            val.value = v;
            removeFromCache(val);
            cap--;
        } else if (cap == totalCap) {
            KV prev = tail.prev;
            cacheMap.remove(prev.key);
            removeFromCache(prev);
            cap--;
        }
        addToCahce(val);
        cap++;
        printList();
    }


    private void printList() {
        if (head == null) return;
        //System.out.println("Head "+head.key);
        //System.out.println("tail "+tail.key);
        KV ind = head.next;
        while (ind != tail) {
            System.out.println("ind "+ind.key+" "+ind.value);
            ind = ind.next;
        }
        System.out.println("Done print");
    }

    public static void main(String[] args) {
        /* [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]] */
        /*
        ["LRUCache","put","put","get","put","get","put","get","get","get"]
[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

    [null,null,null,1,null,-1,null,-1,3,4]
         */
        LRUCache lu = new LRUCache(2);
        lu.put(1,1);
        lu.put(2,2);
        System.out.println(lu.get(1));
        lu.put(3,3);
        System.out.println(lu.get(2));
        lu.put(4,4);
        System.out.println(lu.get(1));
        System.out.println(lu.get(3));
        System.out.println(lu.get(4));
        //System.out.println(lu.get(3));

    }



}
