package com.homeauto;

import com.homeauto.devices.DevicesManager;

import lombok.Getter;

public final class HomeAutomation {

    @Getter
    private static DevicesManager devicesManager;

    public static void main(String[] args) {
        System.out.println("[INFO] Starting devices manager");
        devicesManager = new DevicesManager();
    }

}
