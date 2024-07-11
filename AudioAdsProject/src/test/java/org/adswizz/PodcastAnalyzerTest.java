package org.adswizz;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PodcastAnalyzerTest {

    @Test
    public void testMostPopularShowAndDevices() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            PodcastAnalyzer podcastAnalyzer = new PodcastAnalyzer("src/test/resources/Downloads/downloads.txt");

            String mostPopularShow = podcastAnalyzer.getMostPopularShowInCity("san francisco");

            String mostPopularDevice = podcastAnalyzer.getMostPopularDevice();

            System.out.println(mostPopularShow);
            System.out.println(mostPopularDevice);

            String[] outputLines = outContent.toString().split(System.lineSeparator());
            String expectedShowId = "Who Trolled Amber";
            int expectedShowDownloadCount = 24;
            String expectedDevice = "mobiles & tablets";
            int expectedDeviceDownloadCount = 60;

            assertEquals("Most popular show is: " + expectedShowId + "\nNumber of downloads is: " + expectedShowDownloadCount, outputLines[0].trim());
            assertEquals("Most popular device is: " + expectedDevice + "\nNumber of downloads is: " + expectedDeviceDownloadCount, outputLines[1].trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
