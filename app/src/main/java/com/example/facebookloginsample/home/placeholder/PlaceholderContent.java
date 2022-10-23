package com.example.facebookloginsample.home.placeholder;

import com.example.facebookloginsample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<Integer, PlaceholderItem> ITEM_MAP = new HashMap<Integer, PlaceholderItem>();

    private static final int COUNT = 3;

    private static List<Integer> resIDs = new ArrayList<>();

    static {
        resIDs.add(R.drawable.vegitables);
        resIDs.add(R.drawable.transport);
        resIDs.add(R.drawable.animals);
        // Add some sample items.
        for (int i = 0; i <COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.content, item);
    }

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(resIDs.get(position));
    }


    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {

        public final int content;


        public PlaceholderItem(int content) {
            this.content = content;
        }
    }
}