package com.fitness.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class ClubDto {

    private UUID uuid;
    private String name;
    private Double latitude;
    private Double longitude;
    private boolean isFavorite;

    public ClubDto(UUID uuid, String name, Double latitude, Double longitude, boolean isFavorite) {
        this.uuid = uuid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFavorite = isFavorite;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
