package com.homeauto.devices.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SolarMonitorV1DataModel {

    @JsonProperty("PV_VOLTAGE")
    private float solarPanelVoltage;
    @JsonProperty("PV_POWER")
    private float solarPanelPower;
    @JsonProperty("PV_CURRENT")
    private float solarPanelCurrent;
    @JsonProperty("LOAD_CURRENT")
    private float loadCurrent;
    @JsonProperty("LOAD_POWER")
    private float loadPower;
    @JsonProperty("BATTERY_STATUS")
    private float batteryStatus;
    @JsonProperty("BATERRY_REAL_RATED_POWER")
    private String batteryRealRatedPower;
    @JsonProperty("BATTERY_TYPE")
    private String batteryType;
    @JsonProperty("BATTERY_CAPACITY")
    private float batteryCapacity;
    @JsonProperty("BATTERY_TEMP")
    private float batteryTemperature;
    @JsonProperty("BATTERY_VOLTAGE")
    private float batteryVoltage;
    @JsonProperty("BATTERY_SOC")
    private float batteryStateOfCharge;
    @JsonProperty("BATTERY_CHARGE_CURRENT")
    private float batteryChargeCurrent;
    @JsonProperty("BATTERY_CHARGE_POWER")
    private float batteryChargePower;
    @JsonProperty("BATTERY_OVERALL_CURRENT")
    private float batteryOverallCurrent;
    @JsonProperty("BATTERY_BOOST_VOLTAGE")
    private float batteryBoostVoltage;
    @JsonProperty("BATTERY_EQUALIZATION_VOLTAGE")
    private float batteryEqualizationVoltage;
    @JsonProperty("BATTERY_FLOAT_VOLTAGE")
    private float batteryFloatVoltage;
    @JsonProperty("BATTERY_FLOAT_MIN_VOLTAGE")
    private float batteryFloatMinVoltage;
    @JsonProperty("BATTERY_CHARGING_LIMIT_VOLTAGE")
    private float batteryChargingLimitVoltage;
    @JsonProperty("BATTERY_DISCHARGING_LIMIT_VOLTAGE")
    private float batteryDischargingLimitVoltage;
    @JsonProperty("BATTERY_LOW_VOLTAGE_DISCONNECT")
    private float batteryLowVoltageDisconnect;
    @JsonProperty("BATTERY_LOW_VOLTAGE_RECONNECT")
    private float batteryLowVoltageReconnect;
    @JsonProperty("BATTERY_OVER_VOLTAGE_DISCONNECT")
    private float batteryOverVoltageDisconnect;
    @JsonProperty("BATTERY_OVER_VOLTAGE_RECONNECT")
    private float batteryOverVoltageReconnect;
    @JsonProperty("BATTERY_UNDER_VOLTAGE_SET")
    private float batteryUnderVoltageSet;
    @JsonProperty("BATTERY_UNDER_VOLTAGE_RESET")
    private float batteryUnderVoltageReset;
    @JsonProperty("CONTROLLER_TEMP")
    private float controllerTemperature;
    @JsonProperty("REALTIME_CLOCK")
    private float realtimeClock;
    @JsonProperty("LOAD_FORCE_ONOFF")
    private float loadForceOnOff;
    @JsonProperty("LOAD_MANUAL_ONOFF")
    private float loadManualOnOff;
    @JsonProperty("REMOTE_BATTERY_TEMP")
    private float remoteBatteryTemperature;
    @JsonProperty("GENERATED_ENERGY_TODAY")
    private float generatedEnergyToday;
    @JsonProperty("GENERATED_ENERGY_MONTH")
    private float generatedEnergyMonth;
    @JsonProperty("GENERATED_ENERGY_YEAR")
    private float generatedEnergyYear;
    @JsonProperty("GENERATED_ENERGY_TOTAL")
    private float generatedEnergyTotal;
    @JsonProperty("MAXIMUM_PV_VOLTAGE_TODAY")
    private float maxSolarPanelVoltageToday;
    @JsonProperty("MINIMUM_PV_VOLTAGE_TODAY")
    private float minSolarPanelVoltageToday;
    @JsonProperty("MAXIMUM_BATTERY_VOLTAGE_TODAY")
    private float maxBatteryVoltageToday;
    @JsonProperty("MINIMUM_BATTERY_VOLTAGE_TODAY")
    private float minBatteryVoltageToday;
    @JsonProperty("CHARGING_EQUIPMENT_STATUS")
    private float chargingEquipmentStatus;
    @JsonProperty("DISCHARGING_EQUIPMENT_STATUS")
    private float dischargingEquipmentStatus;
    @JsonProperty("CHARGING_DEVICE_ONOFF")
    private float chargingDeviceOnOff;
    @JsonProperty("HEATSINK_TEMP")
    private float headsinkTemperature;
    @JsonProperty("PC_ONLINE_STATUS")
    private boolean pcOnline;
}
