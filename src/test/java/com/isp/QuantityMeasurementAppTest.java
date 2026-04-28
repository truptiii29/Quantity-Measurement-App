package com.isp;
import com.isp.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // -------- EQUALITY --------
    @Test
    void testEquality() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    // -------- CONVERSION --------
    @Test
    void testConvert() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    // -------- UC6 ADD --------
    @Test
    void testAddition_DefaultUnit() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    // -------- UC7 ADD --------
    @Test
    void testAddition_TargetInches() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_TargetYards() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b,
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(0.6667, result.getValue(), 0.01);
    }

    @Test
    void testAddition_NullTarget() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.QuantityLength.add(a, a, null));
    }
}