package com.ntersol.ntersolmusic.data;

import com.google.gson.annotations.SerializedName;

public class SongTrackRaw {

    @SerializedName("name")
    private String name;

    @SerializedName("profileImageUrl")
    private String profileImageUrl;

    @SerializedName("normalizedProfileImageUrl")
    private String normalizedProfileImageUrl;

    @SerializedName("merchantName")
    private String merchantName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getNormalizedProfileImageUrl() {
        return normalizedProfileImageUrl;
    }

    public void setNormalizedProfileImageUrl(String normalizedProfileImageUrl) {
        this.normalizedProfileImageUrl = normalizedProfileImageUrl;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
