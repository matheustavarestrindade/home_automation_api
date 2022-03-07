package com.homeauto.devices.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.homeauto.devices.models.DeviceModel;
import com.homeauto.devices.models.SolarMonitorV1DataModel;
import com.homeauto.utillities.JsonUtils;

import lombok.Getter;

@JsonInclude(Include.NON_NULL)
public class SolarMonitorV1 extends DeviceImplementation {

    public SolarMonitorV1(DeviceModel model) {
        super(model);
    }

    @Getter
    private SolarMonitorV1DataModel solarMonitorData;

    public void turnOnPc() {
        this.requestDeviceOperation("turn_on");
    }

    @Override
    public void refreshDeviceData() {
        String requestedData = this.requestDeviceOperation("data");
        if (requestedData == null) {
            this.setDataLoaded(false);
            return;
        }
        this.setDataLoaded(true);
        this.setLastDataUpdate(System.currentTimeMillis());
        try {
            this.solarMonitorData = JsonUtils.JSON_MAPPER.readValue(requestedData, SolarMonitorV1DataModel.class);
            System.out.println(this.solarMonitorData.isPcOnline());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
