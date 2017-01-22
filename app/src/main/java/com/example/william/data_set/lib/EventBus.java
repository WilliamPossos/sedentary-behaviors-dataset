package com.example.william.data_set.lib;

/**
 * Created by WilliamStibent on 02/09/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
