package org.adswizz;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpportunityAnalyzerTest {

    @Test
    public void testOpportunities() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            PodcastAnalyzer podcastAnalyzer = new PodcastAnalyzer("src/test/resources/Downloads/downloads.txt");
            OpportunityAnalyzer opportunityAnalyzer = new OpportunityAnalyzer(podcastAnalyzer.getDownloads());
            opportunityAnalyzer.printOpportunities();

            String[] outputLines = outContent.toString().split(System.lineSeparator());
            String expectedShowNumberOne = "Stuff You Should Know";
            String expectedShowNumberTwo = "Who Trolled Amber";
            String expectedShowNumberThree = "Crime Junkie";
            String expectedShowNumberFour = "The Joe Rogan Experience";

            int expectedPrerollNumberOne = 40;
            int expectedPrerollNumberTwo = 30;
            int expectedPrerollNumberThree = 10;


            assertEquals("Show ID: " + expectedShowNumberOne + ", Number of preroll opportunities: " + expectedPrerollNumberOne, outputLines[0].trim());
            assertEquals("Show ID: " + expectedShowNumberTwo + ", Number of preroll opportunities: " + expectedPrerollNumberOne, outputLines[1].trim());
            assertEquals("Show ID: " + expectedShowNumberThree + ", Number of preroll opportunities: " + expectedPrerollNumberTwo, outputLines[2].trim());
            assertEquals("Show ID: " + expectedShowNumberFour + ", Number of preroll opportunities: " + expectedPrerollNumberThree, outputLines[3].trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
