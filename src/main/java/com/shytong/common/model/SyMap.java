package com.shytong.common.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author sytong
 * @Package com.shytong.common.model
 * @Description:
 * @date 2018-01-17 19:53
 */
public class SyMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    private static final long         serialVersionUID         = 1L;
    private static final int          DEFAULT_INITIAL_CAPACITY = 16;
    public SyMap(){
        this(DEFAULT_INITIAL_CAPACITY, false);
    }
    public SyMap(Map<K,V> map){
        this.map = map;
    }

    public SyMap(int initialCapacity){
        this(initialCapacity, false);
    }
    private final Map<K,V> map;
    public SyMap(int initialCapacity, boolean ordered){
        if (ordered) {
            map = new LinkedHashMap<K,V>(initialCapacity);
        } else {
            map = new HashMap<K, V>(initialCapacity);
        }
    }

    public String getString(K key){
    return this.get(key)==null?null:this.get(key).toString();
    }

    public Integer getInteger(K key){




        return Integer.parseInt(this.getString(key));
    }

    public static <K,V> SyMap<K,V> map(){

        return new SyMap<>();
    }
    public static <K,V> SyMap<K,V> map(K key, V value){

        return new SyMap<K,V>().syPut(key,value);
    }
    public SyMap<K,V> syPut(K key, V value){

        this.put(key,value);
        return this;
    };

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key,value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }


    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }



}
