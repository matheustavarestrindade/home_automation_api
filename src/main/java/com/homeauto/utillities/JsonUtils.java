package com.homeauto.utillities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.homeauto.devices.implementation.SolarMonitorV1;

public class JsonUtils {

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    static {
        JSON_MAPPER.registerSubtypes(new NamedType(SolarMonitorV1.class, "SolarMonitorV1"));
    }
}
