package com.isp;

public class QuantityMeasurementApp {

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

        // -------- CONVERT INSTANCE METHOD --------
        public QuantityLength convertTo(LengthUnit target) {
            double base = unit.toBase(value);
            double converted = target.fromBase(base);
            return new QuantityLength(converted, target);
        }

        // -------- UC6 ADD (default unit = first operand) --------
        public static QuantityLength add(QuantityLength a, QuantityLength b) {
            return add(a, b, a.unit);
        }

        // -------- UC7 ADD (target unit specified) --------
        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit target) {

            if (a == null || b == null || target == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = a.unit.toBase(a.value) + b.unit.toBase(b.value);
            double result = target.fromBase(sumFeet);

            return new QuantityLength(result, target);
        }
    }

    // -------- STATIC CONVERT (UC5) --------
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid number");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double base = source.toBase(value);
        return target.fromBase(base);
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equality: " + a.equals(b));

        System.out.println("Convert 1 ft to inch: " +
                a.convertTo(LengthUnit.INCH).getValue());

        System.out.println("Add default: " +
                QuantityLength.add(a, b).getValue() + " " +
                QuantityLength.add(a, b).getUnit());

        System.out.println("Add in yards: " +
                QuantityLength.add(a, b, LengthUnit.YARDS).getValue());
    }
}