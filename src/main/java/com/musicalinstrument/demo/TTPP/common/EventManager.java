package com.musicalinstrument.demo.TTPP.common;

import com.musicalinstrument.demo.TTPP.Dao_for_brands.IDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<String, List<IEventListener>> listeners = new HashMap<>();

    public void subscribe (String eventType, IEventListener listener) {
        if (listeners.containsKey(eventType)) {
            List<IEventListener> listenersArray = listeners.get(eventType);
            listenersArray.add(listener);
        } else {
            List <IEventListener> array = new ArrayList<>();
            array.add(listener);
            listeners.put(eventType, array);
        }
    }

    public void unsubscribe (String eventType, IEventListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify (String eventType, IDAO dao) {
        for (IEventListener listener: listeners.get(eventType))
            listener.update(dao);
    }

}
