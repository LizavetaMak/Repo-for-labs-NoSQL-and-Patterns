package com.musicalinstrument.demo.TTPP.memento;

import com.musicalinstrument.demo.jpa.Brand;

import java.util.HashMap;
import java.util.Stack;

public class BrandsHistory {
    private HashMap<Long, Stack<BrandMemento>> map = new HashMap<>();

    public Brand revert (Long Id) {
        if (map.containsKey(Id)) {
            Stack<BrandMemento> stateHistory = map.get(Id);

            if (stateHistory.size() > 0) {
                BrandMemento brandMemento = stateHistory.pop();

                return brandMemento.restore();
            }
        }

        return null;
    }

    public void clearHistoryForBrand (Long cityId) {
        map.remove(cityId);
    }

    public void rememberState (Long cityId, BrandMemento memento) {
        if (!map.containsKey(cityId)) {
            Stack<BrandMemento> stateHistory = new Stack<>();
            stateHistory.add(memento);

            map.put(cityId, stateHistory);
        } else {
            Stack<BrandMemento> stateHistory = map.get(cityId);
            stateHistory.add(memento);

            map.put(cityId, stateHistory);
        }
    }
}
