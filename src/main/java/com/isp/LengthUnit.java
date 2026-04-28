package com.isp;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CM(1.0 / 30.48);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    public double toBase(double value) {
        return value * toFeet;
    }

    public double fromBase(double base) {
        return base / toFeet;
    }
}