package Structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Weighted random selection using cumulative weight buckets.
// Each entry stores the running total weight; getRandom() picks a random
// value in [0, total] and returns the first entry whose cumulative weight
// meets or exceeds it.
public class WeightedRandomBag<T> {
    private class Entry {
        double accumulatedWeight;
        T object;
    }

    private List<Entry> entries = new ArrayList<>();
    private double accumulatedWeight;
    private Random rand = new Random();

    public void addEntry(T object, double weight) {
        accumulatedWeight += weight;
        Entry e = new Entry();
        e.object = object;
        e.accumulatedWeight = accumulatedWeight;
        entries.add(e);
    }

    // Clears all entries so the bag can be repopulated (used when banners reload).
    public void purge(){
        entries.clear();
        accumulatedWeight = 0;
    }

    public T getRandom() {
        double r = rand.nextDouble() * accumulatedWeight;
        for (Entry entry : entries) {
            if (entry.accumulatedWeight >= r) {
                return entry.object;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }
}
