package com.wangxshen.trie;

/**
 * @Author WangShen
 * @Date 2020/9/28 17:43
 * @Version 1.0
 */
public interface TrieInterface {
    public int search(String word);
    public void insert(String word);
    public void delete(String word);
    public int prefixNumber(String prefix);
}
