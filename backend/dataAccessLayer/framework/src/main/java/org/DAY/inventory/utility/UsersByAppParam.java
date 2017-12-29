package org.DAY.inventory.utility;

public class UsersByAppParam {
    private int appId;
    private int zoneId;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return "UsersByAppParam{" +
                "appId=" + appId +
                ", zoneId=" + zoneId +
                '}';
    }
}
