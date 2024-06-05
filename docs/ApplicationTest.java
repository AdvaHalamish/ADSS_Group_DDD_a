/*package Tests;

import static org.junit.jupiter.api.Assertions.*;

import Menus.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ApplicationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    public void testEndToEnd() {
        // Simulated user input
        String simulatedUserInput = String.join("\n",
                "1",  // Worker Menu
                "1",  // Insert Item
                "Apple", "10.0", "Apple Inc.", "A001", "Fruits", "Fresh", "1.0", "Store", "2024-06-02", "Available",
                "3",  // Back to Main Menu
                "2",  // Management Menu
                "1",  // Apply Discount
                "Apple", "0.1", "2024-06-05", "2024-06-10",
                "5",  // Generate Report
                "1",  // Report for Categories
                "Fruits",
                "3",  // Generate Report for Defective Products
                "4"   // Exit
        ) + "\n";

        // Redirect System.in to read simulated user input
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        // Call main method
        Main.main(new String[]{});

        // Restore System.in
        System.setIn(originalIn);

        // Verify output
        String output = outContent.toString();
        assertTrue(output.contains("Item inserted successfully."));
        assertTrue(output.contains("Discount applied to product Apple"));
        assertTrue(output.contains("Category: Fruits"));
        // Add more assertions for other expected output
    }
}*/
