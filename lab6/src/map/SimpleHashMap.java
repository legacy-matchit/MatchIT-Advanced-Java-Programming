package map;

public class SimpleHashMap<K,V> implements Map<K,V>{

    Entry<K,V>[] entris;

    public SimpleHashMap(){
        this(100);
    }
    public SimpleHashMap(int capaticy){
        entris = (Entry<K,V>[]) new Entry[capaticy];
    }

    public V get(Object key){
        K k = (K)key;
        int i = index(k);
        Entry<K,V> entry = find(i,k);
        return (entry != null)? entry.getValue() : null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public V put(K k, V v){

        if(size() > entris.length * 0.7 ){
            rehash();
        }
        /***
         * 엔트리를 파인드 메소드로 찾아옴 만약 널이 아니면 전에 있던 값을 반환
         * 널이면 새로운 엔트리로 초기화해서 배열에 넣어둠 리턴은
         */
        Entry<K,V> entry = find(index(k),k);
        if(entry != null){
            V tem = entry.getValue();
            entry.setValue(v);
            return tem;
        }else{
            entry = new Entry<>(k,v);
            entris[index(k)] = entry;
            return null;
        }
    }
    public V remove(Object arg0){
        return null;
    }
    public int size(){
        int size = 0;
        for (int i = 0; i < entris.length; i++){
            if(entris[i] != null){
                size++;
            }
        }
        return size;
    }
    public String show(){
        StringBuilder sb = new StringBuilder();
        for (Entry<K,V> entry:
             entris) {
            if(entry != null){
                sb.append(entry+"\n");
            }
        }
        return sb.toString();
    }
    private int index(K key){
        return Math.abs(key.hashCode() % entris.length);
    }
    private Entry<K,V> find(int index, K key){

        if(entris[index] != null){
            if(entris[index].getKey() == key ||((Comparable<K>)entris[index].getKey()).compareTo(key) == 0){
                return entris[index];
            }
        }

        return null;
    }
    private void rehash(){
        Entry<K,V>[] tem = (Entry<K,V>[]) new Entry[entris.length*2];
        for(int i = 0; i < entris.length; i++){
            tem[i] = entris[i];
        }
        entris =  (Entry<K,V>[]) new Entry[tem.length];
        for(int i = 0; i < tem.length; i++){
            entris[i] = tem[i];
        }
    }

    private static class Entry<K,V> implements Map.Entry<K,V>{

        K key;
        V value;

        Entry(K k,V v){
            this.key = k;
            this.value = v;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public String toString(){
            return key + " = " + value;
        }
    }


}
