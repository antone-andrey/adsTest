package org.adswizz;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            PodcastAnalyzer podcastAnalyzer = new PodcastAnalyzer("src/test/resources/Downloads/downloads.txt");

            System.out.println(podcastAnalyzer.getMostPopularShowInCity("san francisco"));
            System.out.println(podcastAnalyzer.getMostPopularDevice());

            OpportunityAnalyzer opportunityAnalyzer = new OpportunityAnalyzer(podcastAnalyzer.getDownloads());
            opportunityAnalyzer.printOpportunities();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
