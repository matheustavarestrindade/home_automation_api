package com.homeauto.devices;

import java.util.Collection;
import java.util.HashMap;

import com.homeauto.devices.implementation.DeviceImplementation;
import com.homeauto.devices.utilities.DeviceIPDiscover;

public class DevicesManager {

    private DeviceIPDiscover deviceDiscover;
    public static final int NETWORK_DEVICES_PORT = 7858;

    private HashMap<String, DeviceImplementation> devices_by_ip_address = new HashMap<>();

    public DevicesManager() {
        this.initializeIPDiscoverService();
    }

    public Collection<DeviceImplementation> getRegisteredDevices() {
        return this.devices_by_ip_address.values();
    }

    /*
     * 
     * Private Methods
     * 
     */

    private void initializeIPDiscoverService() {
        this.deviceDiscover = new DeviceIPDiscover(devices_by_ip_address);
        Thread t = new Thread(this.deviceDiscover);
        t.start();
    }

}
