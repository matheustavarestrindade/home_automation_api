package com.homeauto.devices.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.homeauto.devices.DevicesManager;
import com.homeauto.devices.models.DeviceModel;
import com.homeauto.devices.utilities.DeviceType;

import lombok.Data;

@Data
public abstract class DeviceImplementation {

    private DeviceType type;
    private String UUID;
    private long uptime;

    private boolean online;
    private String ipAddress;

    private boolean dataLoaded;
    private long lastDataUpdate;

    public DeviceImplementation(DeviceModel model) {
        this.type = model.getType();
        this.UUID = model.getUUID();
        this.uptime = model.getUptime();
        this.online = model.isOnline();
        this.ipAddress = model.getIpAddress();
        this.dataLoaded = false;
        this.lastDataUpdate = 0;
    }

    public abstract void refreshDeviceData();

    public void resetNetworkConfiguration() {
        requestDeviceOperation("reset_network");
    }

    protected String requestDeviceOperation(String operation) {
        try {
            URL url = new URL("http://" + this.ipAddress + ":" + DevicesManager.NETWORK_DEVICES_PORT + "/" + operation);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return content.toString();
            }
            con.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return null;

    }

}
