package com.homeauto.devices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.homeauto.devices.utilities.DeviceType;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DeviceModel {

    @JsonProperty("type")
    private DeviceType type;
    @JsonProperty("uuid")
    private String UUID;
    @JsonProperty("uptime")
    private long uptime;

    @JsonIgnore
    private boolean online;
    @JsonIgnore
    private String ipAddress;

}
