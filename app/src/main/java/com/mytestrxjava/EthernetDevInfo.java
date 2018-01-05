package com.mytestrxjava;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yomoo on 2017/8/21.
 */

public class EthernetDevInfo implements Parcelable {
    private String dev_name;
    private String ipaddr;
    private String netmask;
    private String route;
    private String dns;
    private String mode;
    public static final String ETH_CONN_MODE_DHCP = "dhcp";
    public static final String ETH_CONN_MODE_MANUAL = "manual";

    public EthernetDevInfo() {
        dev_name = null;
        ipaddr = null;
        dns = null;
        route = null;
        netmask = null;
        mode = ETH_CONN_MODE_DHCP;
    }

    protected EthernetDevInfo(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EthernetDevInfo> CREATOR = new Creator<EthernetDevInfo>() {
        @Override
        public EthernetDevInfo createFromParcel(Parcel in) {
            EthernetDevInfo info = new EthernetDevInfo();
            info.setDev_name(in.readString());
            info.setIpaddr(in.readString());
            info.setNetmask(in.readString());
            info.setRoute(in.readString());
            info.setDns(in.readString());
            info.setMode(in.readString());
            return info;
        }

        @Override
        public EthernetDevInfo[] newArray(int size) {
            return new EthernetDevInfo[size];
        }
    };

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
