public class FormattedStringWithAnsiFormatting {
    public static void main(String[] args) {
        // ANSI escape codes for text formatting
        String reset = "\u001B[0m"; // Reset all attributes
        String red = "\u001B[31m";   // Red text color
        String green = "\u001B[32m"; // Green text color
        String bold = "\u001B[1m";   // Bold text

        // Format the string with placeholders
        String formattedString = String.format("This is %sred%s text and this is %sgreen%s text. This is %sbold%s text.",
                red, reset, green, reset, bold, reset);

        // Print the formatted string
        System.out.println(formattedString);
    }
}
