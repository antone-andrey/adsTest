package org.adswizz;

import java.util.*;
import java.util.stream.Collectors;

public class OpportunityAnalyzer {

    private final List<DownloadInfo> downloads;

    public OpportunityAnalyzer(List<DownloadInfo> downloads) {
        this.downloads = downloads;
    }

    public void printOpportunities() {
        Map<String, Long> opportunitiesCounts = downloads.stream()
                .flatMap(download -> download.getAdBreakIndex().stream()
                        .filter(adBreak -> adBreak.equals("preroll"))
                        .map(adBreak -> download.getShowId()))
                .collect(Collectors.groupingBy(showId -> showId, Collectors.counting()));

        opportunitiesCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Show ID: " + entry.getKey() + ", Number of preroll opportunities: " + entry.getValue()));
    }
}
