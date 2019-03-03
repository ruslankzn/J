package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    Storage storage;
    OriginalRetriever retriever;
    LRUCache<Long,Object> cache;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        this.retriever = new OriginalRetriever(storage);
        cache = new LRUCache<>(16);
    }

    @Override
    public Object retrieve(long id) {
        Object o = cache.find(id);
        if(o == null){
            o = retriever.retrieve(id);
            cache.set(id, o);
        }
        return o;
    }
}
