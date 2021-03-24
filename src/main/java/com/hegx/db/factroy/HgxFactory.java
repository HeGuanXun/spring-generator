package com.hegx.db.factroy;

/**
 * 对象工厂
 */
public class HgxFactory {
        public static Factory getInstance(String type) {
            Object factory = null;
            try {
                factory = Class.forName(type).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return (Factory) factory;
        }
}