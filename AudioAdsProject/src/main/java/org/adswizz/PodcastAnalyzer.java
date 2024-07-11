package org.adswizz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PodcastAnalyzer {

    private final List<DownloadInfo> downloads;

    public PodcastAnalyzer(String filePath) throws IOException {
        this.downloads = loadDownloads(filePath);
    }

    private List<DownloadInfo> loadDownloads(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> {
                try {
                    JsonNode node = objectMapper.readTree(line);
                    String showId = node.path("downloadIdentifier").path("showId").asText();
                    String city = node.path("city").asText().toLowerCase().trim();
                    String deviceType = node.path("deviceType").asText().toLowerCase().trim();
                    List<String> adBreakIndex = new ArrayList<>();
                    node.path("opportunities").forEach(opportunity -> {
                        opportunity.path("positionUrlSegments").path("aw_0_ais.adBreakIndex").forEach(innerNode -> {
                            adBreakIndex.add(innerNode.asText()); // Assuming adBreakIndex is a List<Integer>
                        });
                    });
                    return new DownloadInfo(showId, city, deviceType, adBreakIndex);
                } catch (IOException e) {
                    System.err.println("Error parsing line: " + line);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
    }

    public List<DownloadInfo> getDownloads() {
        return downloads;
    }

    public String getMostPopularShowInCity(String city) {
        Map<String, Long> downloadCounts = downloads.stream()
                .filter(download -> city.equals(download.getCity()))
                .collect(Collectors.groupingBy(DownloadInfo::getShowId, Collectors.counting()));

        return downloadCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> "Most popular show is: " + entry.getKey() + "\nNumber of downloads is: " + entry.getValue())
                .orElse("No downloads found in " + city);
    }

    public String getMostPopularDevice() {
        Map<String, Long> deviceCounts = downloads.stream()
                .collect(Collectors.groupingBy(DownloadInfo::getDeviceType, Collectors.counting()));

        return deviceCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> "Most popular device is: " + entry.getKey() + "\nNumber of downloads is: " + entry.getValue())
                .orElse("No downloads found");
    }
}
