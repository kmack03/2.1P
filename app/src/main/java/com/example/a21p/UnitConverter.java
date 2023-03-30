package com.example.a21p;

public class UnitConverter {

    private final double[] conversionFactors = {1.0, 0.01, 1000, 0.0254, 0.3048, 0.9144, 1609.344};

    public double convert(String fromUnit, String toUnit, String input) {
        // Parse the input value
        double inputValue = Double.parseDouble(input);

        // Get the indices of the selected units in the conversion factors array
        int fromIndex = getUnitIndex(fromUnit);
        int toIndex = getUnitIndex(toUnit);


        // Convert the input value to the base unit
        double baseValue = inputValue * conversionFactors[fromIndex];

        // Convert the base value to the target unit
        double targetValue = baseValue / conversionFactors[toIndex];

        return targetValue;
    }

    private int getUnitIndex(String unit) {
        switch (unit) {
            case "m":
                return 0;
            case "cm":
                return 1;
            case "km":
                return 2;
            case "in":
                return 3;
            case "ft":
                return 4;
            case "yr":
                return 5;
            case "mi":
                return 6;
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
}
