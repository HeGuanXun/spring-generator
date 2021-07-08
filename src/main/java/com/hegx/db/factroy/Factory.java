package com.hegx.db.factroy;


import java.util.ArrayList;

/**
 * 使用工厂创建对象
 */
public interface Factory {

    /**
     * @return
     */
    public <T> ArrayList<T> buildDatas();
}
