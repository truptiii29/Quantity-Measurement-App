package com.isp;

public class QuantityMeasurementApp {

    // -------- FEET CLASS (UC1) --------
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- INCHES CLASS (UC2) --------
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- HELPER METHODS --------
    public static boolean compareFeet(double v1, double v2) {
        return new Feet(v1).equals(new Feet(v2));
    }

    public static boolean compareInches(double v1, double v2) {
        return new Inches(v1).equals(new Inches(v2));
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("Feet equality: " + compareFeet(1.0, 1.0));
        System.out.println("Inches equality: " + compareInches(1.0, 1.0));
    }
}