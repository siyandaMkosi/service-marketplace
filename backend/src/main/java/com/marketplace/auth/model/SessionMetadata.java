package com.marketplace.auth.model;

public record SessionMetadata(

    String ipAddress,

    String userAgent,

    String deviceName

) {
}
