package com.homeauto.devices.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.homeauto.devices.DevicesManager;
import com.homeauto.devices.implementation.DeviceImplementation;
import com.homeauto.devices.implementation.SolarMonitorV1;
import com.homeauto.devices.models.DeviceModel;
import com.homeauto.utillities.JsonUtils;

public class DeviceIPDiscover implements Runnable {

    private static final int NETWORK_RANGE_MIN = 0;
    private static final int NETWORK_RANGE_MAX = 255;

    private static final Executor THREAD_EXECUTOR = Executors.newFixedThreadPool(10); // 10 threads

    private String LOCAL_ADDRESS_IP = "xxx.xxx.xxx.xxx";
    private HashMap<String, DeviceImplementation> devices_by_ip_address;

    public DeviceIPDiscover(HashMap<String, DeviceImplementation> devices_by_ip_address) {
        this.devices_by_ip_address = devices_by_ip_address;
        try {
            Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
            String localHostAddress = InetAddress.getLocalHost().getHostAddress();
            Matcher matcher = ipPattern.matcher(localHostAddress);
            if (matcher.find() && matcher.groupCount() == 4) {
                LOCAL_ADDRESS_IP = matcher.group(1) + "." + matcher.group(2) + "." + matcher.group(3) + ".{RANGE}";
                // 192.168.0.{RANGE}
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            System.out.println("[INFO] Scaning devices...");
            for (int range = NETWORK_RANGE_MIN; range <= NETWORK_RANGE_MAX; range++) {

                final String IP_ADDRESS = LOCAL_ADDRESS_IP.replace("{RANGE}", String.valueOf(range));
                final String IP_TO_SCAN = "http://" + IP_ADDRESS + ":" + DevicesManager.NETWORK_DEVICES_PORT + "/info";

                THREAD_EXECUTOR.execute(() -> {

                    DeviceModel model = handleDeviceDiscover(IP_TO_SCAN, IP_ADDRESS);

                    if (devices_by_ip_address.containsKey(IP_ADDRESS) && model == null) {
                        devices_by_ip_address.get(IP_ADDRESS).setOnline(false);
                        devices_by_ip_address.get(IP_ADDRESS).setOnline(false);
                        return;
                    } else if (devices_by_ip_address.containsKey(IP_ADDRESS)) {
                        devices_by_ip_address.get(IP_ADDRESS).setOnline(true);
                        devices_by_ip_address.get(IP_ADDRESS).setUptime(model.getUptime());
                        return;
                    }

                    if (model != null) {
                        DeviceImplementation implementation = null;
                        switch (model.getType()) {
                        case SOLAR_MONITOR_V1:
                            implementation = new SolarMonitorV1(model);
                            break;
                        default:
                            break;
                        }

                        if (implementation != null) {
                            implementation.refreshDeviceData();
                            devices_by_ip_address.put(IP_ADDRESS, implementation);
                        }

                    }

                });
            }

            try {
                Thread.sleep(1000 * 60 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private DeviceModel handleDeviceDiscover(String IP_TO_SCAN, String IP_ADDRESS) {

        try {
            URL url = new URL(IP_TO_SCAN);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String contentString = content.toString();

                DeviceModel model = JsonUtils.JSON_MAPPER.readValue(contentString, DeviceModel.class);

                model.setIpAddress(IP_ADDRESS);
                model.setOnline(true);
                return model;
            }
            con.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return null;
    }

}
