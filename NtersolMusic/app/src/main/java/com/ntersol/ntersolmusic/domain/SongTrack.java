package com.ntersol.ntersolmusic.domain;


public class SongTrack {
    private final String name;
    private final String profileImageUrl;
    private final String normalizedProfileImageUrl;
    private final String merchantName;

    public SongTrack(String name, String profileImageUrl, String normalizedProfileImageUrl, String merchantName) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.normalizedProfileImageUrl = normalizedProfileImageUrl;
        this.merchantName = merchantName;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNormalizedProfileImageUrl() {
        return normalizedProfileImageUrl;
    }

    public String getMerchantName() {
        return merchantName;
    }
}
