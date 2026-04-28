package com.isp;

public class QuantityMeasurementApp {

    // -------- ENUM FOR UNITS --------
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
    }

    // -------- GENERIC CLASS --------
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid number");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            double epsilon = 0.0001;
            return Math.abs(this.toFeet() - other.toFeet()) < epsilon;
        }
    }

    // -------- UC5: CONVERSION METHOD --------
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid number");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // convert to base (feet)
        double valueInFeet = source.toBase(value);

        // convert to target
        return valueInFeet / target.toBase(1.0);
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {

        QuantityLength f1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength f2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equal: " + f1.equals(f2));

        double result = convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        System.out.println("1 foot in inches = " + result);
    }
}