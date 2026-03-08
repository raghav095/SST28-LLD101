package com.example.map;

import java.util.List;

/**
 * Entry point for the demo.
 */
public class App {

    public static void main(String[] args) {
        int n = 30_000;

        MapDataSource ds = new MapDataSource();
        MarkerStyleFactory factory = new MarkerStyleFactory();
        List<MapMarker> markers = ds.loadMarkers(n, factory);

        new MapRenderer().render(markers);

        System.out.println();
        System.out.println("Run QuickCheck to verify Flyweight sharing:");
        System.out.println("  java com.example.map.QuickCheck");
    }
}
