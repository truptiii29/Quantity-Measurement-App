package com.isp;

public class QuantityMeasurementApp {

    // -------- ENUM --------
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

    // -------- QUANTITY CLASS --------
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

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        // -------- EQUALITY --------
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            double epsilon = 0.0001;
            return Math.abs(this.toFeet() - other.toFeet()) < epsilon;
        }

        // -------- UC6 ADD (default unit = first operand) --------
        public static QuantityLength add(QuantityLength a, QuantityLength b) {
            return add(a, b, a.unit);
        }

        // -------- UC7 ADD (with target unit) --------
        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {

            if (a == null || b == null) {
                throw new IllegalArgumentException("Null operand");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumFeet = a.toFeet() + b.toFeet();

            double resultValue = sumFeet / targetUnit.toBase(1.0);

            return new QuantityLength(resultValue, targetUnit);
        }
    }

    // -------- UC5 CONVERSION --------
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid number");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double valueInFeet = source.toBase(value);
        return valueInFeet / target.toBase(1.0);
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equality: " + a.equals(b));

        System.out.println("Convert 1 ft to inch: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("Addition (default): " +
                QuantityLength.add(a, b).getValue() + " " +
                QuantityLength.add(a, b).getUnit());

        System.out.println("Addition (target = YARDS): " +
                QuantityLength.add(a, b, LengthUnit.YARDS).getValue() + " YARDS");
    }
}