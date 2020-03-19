package io.nuls.common.pojo.vo;

/**
 * 下拉框选项VO
 *
 * @author Long
 * @date 2020/03/16
 */
public class OptionVO<K, V> extends AbstractVO {

    private K key;

    private V value;

    public OptionVO(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}

