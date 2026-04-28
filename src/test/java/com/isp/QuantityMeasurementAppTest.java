package com.isp;
import com.isp.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // -------- EQUALITY TESTS --------
    @Test
    void testFeetToInchEquality() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    @Test
    void testDifferentValues() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(a.equals(b));
    }

    // -------- CONVERSION TESTS --------
    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testCmToInch() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CM,
                        QuantityMeasurementApp.LengthUnit.INCH),
                0.0001);
    }

    // -------- ADDITION TESTS (UC6) --------
    @Test
    void testAddFeetAndInch() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddSameUnit() {
        var a = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddWithZero() {
        var a = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(0.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddNegative() {
        var a = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(-2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength.add(a, b);

        assertEquals(3.0, result.getValue());
    }
}