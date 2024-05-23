package org.example.domain;

public class Plane {
    private int value;
    private final int[] coordinates;

    public Plane(int value, int[] coordinates) {
        this.value = value;
        this.coordinates = coordinates;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public Plane clone() {
        return new Plane(this.value, this.coordinates.clone());
    }
}
