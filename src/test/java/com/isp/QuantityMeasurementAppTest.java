package com.isp;
import com.isp.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    @Test
    void testConvertTo() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        var result = a.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    void testStaticConvert() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCH),
                0.0001);
    }

    @Test
    void testAddition_DefaultUnit() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(0.6667, result.getValue(), 0.01);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testNaN() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}