package org.adswizz;

import java.util.List;

public class DownloadInfo {
    String showId;
    String city;
    String deviceType;
    List<String> adBreakIndex;

    public DownloadInfo(String showId, String city, String deviceType, List<String> adBreakIndex) {
        this.showId = showId;
        this.city = city;
        this.deviceType = deviceType;
        this.adBreakIndex = adBreakIndex;
    }

    public String getShowId() {
        return showId;
    }

    public String getCity() {
        return city;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public List<String> getAdBreakIndex() {
        return adBreakIndex;
    }
}
