package com.example.a21p;

public class UnitConverter {

    private final double[] conversionFactors = {1.0, 0.01, 1000, 0.0254, 0.3048, 0.9144, 1609.344, 1.0, 1000, 0.0283, 0.4536};

    public double convert(String fromUnit, String toUnit, String input) {
        // Parse the input value
        double inputValue = Double.parseDouble(input);

        // Get the indices of the selected units in the conversion factors array
        int fromIndex = getUnitIndex(fromUnit);
        int toIndex = getUnitIndex(toUnit);

        if (fromIndex <= 10) {
            // Convert the input value to the base unit
            double baseValue = inputValue * conversionFactors[fromIndex];

            // Convert the base value to the target unit
            double targetValue = baseValue / conversionFactors[toIndex];

            return targetValue;
        }

        else {
            switch (fromIndex) {
                case (11):
                    if (toIndex == 11) {
                        return inputValue;
                    }
                    if(toIndex == 12) {
                        double returnValue = inputValue * 9;
                        returnValue = returnValue / 5;
                        returnValue = returnValue + 32;
                        return returnValue;
                    }
                    if (toIndex == 13) {
                        return (inputValue - 273.15);
                    }
                case(12):
                    if (toIndex == 11) {
                        double returnValue = inputValue - 32;
                        returnValue = returnValue * 5;
                        returnValue = returnValue / 9;
                        return returnValue;
                    }
                    if(toIndex == 12) {
                        return inputValue;
                    }
                    if (toIndex == 13) {
                        double returnValue = inputValue - 32;
                        returnValue = returnValue * 5;
                        returnValue = returnValue / 9;
                        returnValue = returnValue - 273.15;
                        return returnValue;
                    }

                case(13):
                    if (toIndex == 11) {
                        return (inputValue + 273.15);
                    }
                    if(toIndex == 12) {
                        double returnValue = inputValue * 9;
                        returnValue = returnValue/5;
                        returnValue = returnValue - 459.67;
                        return returnValue;
                    }
                    if (toIndex == 13) {
                        return inputValue;
                    }
                default:
                    return -1;
            }
        }
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
            case "kg":
                return 7;
            case "tonne":
                return 8;
            case "oz":
                return 9;
            case "lb":
                return 10;
            case "c":
                return 11;
            case "f":
                return 12;
            case "k":
                return 13;
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
}
