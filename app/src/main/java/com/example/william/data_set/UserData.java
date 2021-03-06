package com.example.william.data_set;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "USER_DATA".
 */
@Entity
public class UserData {

    @Id(autoincrement = true)
    private Long id;
    private Long timestamp;
    private Double bandAccX;
    private Double bandAccY;
    private Double bandAccZ;
    private Float bandAltimeterRate;
    private Integer bandAmbientLight;
    private Double bandBarometerAir;
    private Double bandBarometerTemp;
    private Integer bandGsr;
    private Double bandGyrX;
    private Double bandGyrY;
    private Double bandGyrZ;
    private Integer bandHeartRate;
    private String bandQoHR;
    private Long bandPedometer;
    private Double bandRR;
    private Double bandSkinTemperature;
    private String bandUVindex;
    private Float mobileAccX;
    private Float mobileAccY;
    private Float mobileAccZ;
    private Float mobileGyrX;
    private Float mobileGyrY;
    private Float mobileGyrZ;
    private Float mobileMagX;
    private Float mobileMagY;
    private Float mobileMagZ;
    private Float mobileGraX;
    private Float mobileGraY;
    private Float mobileGraZ;
    private Float mobileLinX;
    private Float mobileLinY;
    private Float mobileLinZ;
    private Float mobileRotX;
    private Float mobileRotY;
    private Float mobileRotZ;
    private Float mBarometer;
    private Integer iceBeacon;
    private Integer mintBeacon;
    private Integer bbBeacon;
    private Integer iceBeacon2;
    private Integer mintBeacon2;
    private Integer bbBeacon2;
    private long activityId;

    @Generated
    public UserData() {
    }

    public UserData(Long id) {
        this.id = id;
    }

    @Generated
    public UserData(Long id, Long timestamp, Double bandAccX, Double bandAccY, Double bandAccZ, Float bandAltimeterRate, Integer bandAmbientLight, Double bandBarometerAir, Double bandBarometerTemp, Integer bandGsr, Double bandGyrX, Double bandGyrY, Double bandGyrZ, Integer bandHeartRate, String bandQoHR, Long bandPedometer, Double bandRR, Double bandSkinTemperature, String bandUVindex, Float mobileAccX, Float mobileAccY, Float mobileAccZ, Float mobileGyrX, Float mobileGyrY, Float mobileGyrZ, Float mobileMagX, Float mobileMagY, Float mobileMagZ, Float mobileGraX, Float mobileGraY, Float mobileGraZ, Float mobileLinX, Float mobileLinY, Float mobileLinZ, Float mobileRotX, Float mobileRotY, Float mobileRotZ, Float mBarometer, Integer iceBeacon, Integer mintBeacon, Integer bbBeacon, Integer iceBeacon2, Integer mintBeacon2, Integer bbBeacon2, long activityId) {
        this.id = id;
        this.timestamp = timestamp;
        this.bandAccX = bandAccX;
        this.bandAccY = bandAccY;
        this.bandAccZ = bandAccZ;
        this.bandAltimeterRate = bandAltimeterRate;
        this.bandAmbientLight = bandAmbientLight;
        this.bandBarometerAir = bandBarometerAir;
        this.bandBarometerTemp = bandBarometerTemp;
        this.bandGsr = bandGsr;
        this.bandGyrX = bandGyrX;
        this.bandGyrY = bandGyrY;
        this.bandGyrZ = bandGyrZ;
        this.bandHeartRate = bandHeartRate;
        this.bandQoHR = bandQoHR;
        this.bandPedometer = bandPedometer;
        this.bandRR = bandRR;
        this.bandSkinTemperature = bandSkinTemperature;
        this.bandUVindex = bandUVindex;
        this.mobileAccX = mobileAccX;
        this.mobileAccY = mobileAccY;
        this.mobileAccZ = mobileAccZ;
        this.mobileGyrX = mobileGyrX;
        this.mobileGyrY = mobileGyrY;
        this.mobileGyrZ = mobileGyrZ;
        this.mobileMagX = mobileMagX;
        this.mobileMagY = mobileMagY;
        this.mobileMagZ = mobileMagZ;
        this.mobileGraX = mobileGraX;
        this.mobileGraY = mobileGraY;
        this.mobileGraZ = mobileGraZ;
        this.mobileLinX = mobileLinX;
        this.mobileLinY = mobileLinY;
        this.mobileLinZ = mobileLinZ;
        this.mobileRotX = mobileRotX;
        this.mobileRotY = mobileRotY;
        this.mobileRotZ = mobileRotZ;
        this.mBarometer = mBarometer;
        this.iceBeacon = iceBeacon;
        this.mintBeacon = mintBeacon;
        this.bbBeacon = bbBeacon;
        this.iceBeacon2 = iceBeacon2;
        this.mintBeacon2 = mintBeacon2;
        this.bbBeacon2 = bbBeacon2;
        this.activityId = activityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getBandAccX() {
        return bandAccX;
    }

    public void setBandAccX(Double bandAccX) {
        this.bandAccX = bandAccX;
    }

    public Double getBandAccY() {
        return bandAccY;
    }

    public void setBandAccY(Double bandAccY) {
        this.bandAccY = bandAccY;
    }

    public Double getBandAccZ() {
        return bandAccZ;
    }

    public void setBandAccZ(Double bandAccZ) {
        this.bandAccZ = bandAccZ;
    }

    public Float getBandAltimeterRate() {
        return bandAltimeterRate;
    }

    public void setBandAltimeterRate(Float bandAltimeterRate) {
        this.bandAltimeterRate = bandAltimeterRate;
    }

    public Integer getBandAmbientLight() {
        return bandAmbientLight;
    }

    public void setBandAmbientLight(Integer bandAmbientLight) {
        this.bandAmbientLight = bandAmbientLight;
    }

    public Double getBandBarometerAir() {
        return bandBarometerAir;
    }

    public void setBandBarometerAir(Double bandBarometerAir) {
        this.bandBarometerAir = bandBarometerAir;
    }

    public Double getBandBarometerTemp() {
        return bandBarometerTemp;
    }

    public void setBandBarometerTemp(Double bandBarometerTemp) {
        this.bandBarometerTemp = bandBarometerTemp;
    }

    public Integer getBandGsr() {
        return bandGsr;
    }

    public void setBandGsr(Integer bandGsr) {
        this.bandGsr = bandGsr;
    }

    public Double getBandGyrX() {
        return bandGyrX;
    }

    public void setBandGyrX(Double bandGyrX) {
        this.bandGyrX = bandGyrX;
    }

    public Double getBandGyrY() {
        return bandGyrY;
    }

    public void setBandGyrY(Double bandGyrY) {
        this.bandGyrY = bandGyrY;
    }

    public Double getBandGyrZ() {
        return bandGyrZ;
    }

    public void setBandGyrZ(Double bandGyrZ) {
        this.bandGyrZ = bandGyrZ;
    }

    public Integer getBandHeartRate() {
        return bandHeartRate;
    }

    public void setBandHeartRate(Integer bandHeartRate) {
        this.bandHeartRate = bandHeartRate;
    }

    public String getBandQoHR() {
        return bandQoHR;
    }

    public void setBandQoHR(String bandQoHR) {
        this.bandQoHR = bandQoHR;
    }

    public Long getBandPedometer() {
        return bandPedometer;
    }

    public void setBandPedometer(Long bandPedometer) {
        this.bandPedometer = bandPedometer;
    }

    public Double getBandRR() {
        return bandRR;
    }

    public void setBandRR(Double bandRR) {
        this.bandRR = bandRR;
    }

    public Double getBandSkinTemperature() {
        return bandSkinTemperature;
    }

    public void setBandSkinTemperature(Double bandSkinTemperature) {
        this.bandSkinTemperature = bandSkinTemperature;
    }

    public String getBandUVindex() {
        return bandUVindex;
    }

    public void setBandUVindex(String bandUVindex) {
        this.bandUVindex = bandUVindex;
    }

    public Float getMobileAccX() {
        return mobileAccX;
    }

    public void setMobileAccX(Float mobileAccX) {
        this.mobileAccX = mobileAccX;
    }

    public Float getMobileAccY() {
        return mobileAccY;
    }

    public void setMobileAccY(Float mobileAccY) {
        this.mobileAccY = mobileAccY;
    }

    public Float getMobileAccZ() {
        return mobileAccZ;
    }

    public void setMobileAccZ(Float mobileAccZ) {
        this.mobileAccZ = mobileAccZ;
    }

    public Float getMobileGyrX() {
        return mobileGyrX;
    }

    public void setMobileGyrX(Float mobileGyrX) {
        this.mobileGyrX = mobileGyrX;
    }

    public Float getMobileGyrY() {
        return mobileGyrY;
    }

    public void setMobileGyrY(Float mobileGyrY) {
        this.mobileGyrY = mobileGyrY;
    }

    public Float getMobileGyrZ() {
        return mobileGyrZ;
    }

    public void setMobileGyrZ(Float mobileGyrZ) {
        this.mobileGyrZ = mobileGyrZ;
    }

    public Float getMobileMagX() {
        return mobileMagX;
    }

    public void setMobileMagX(Float mobileMagX) {
        this.mobileMagX = mobileMagX;
    }

    public Float getMobileMagY() {
        return mobileMagY;
    }

    public void setMobileMagY(Float mobileMagY) {
        this.mobileMagY = mobileMagY;
    }

    public Float getMobileMagZ() {
        return mobileMagZ;
    }

    public void setMobileMagZ(Float mobileMagZ) {
        this.mobileMagZ = mobileMagZ;
    }

    public Float getMobileGraX() {
        return mobileGraX;
    }

    public void setMobileGraX(Float mobileGraX) {
        this.mobileGraX = mobileGraX;
    }

    public Float getMobileGraY() {
        return mobileGraY;
    }

    public void setMobileGraY(Float mobileGraY) {
        this.mobileGraY = mobileGraY;
    }

    public Float getMobileGraZ() {
        return mobileGraZ;
    }

    public void setMobileGraZ(Float mobileGraZ) {
        this.mobileGraZ = mobileGraZ;
    }

    public Float getMobileLinX() {
        return mobileLinX;
    }

    public void setMobileLinX(Float mobileLinX) {
        this.mobileLinX = mobileLinX;
    }

    public Float getMobileLinY() {
        return mobileLinY;
    }

    public void setMobileLinY(Float mobileLinY) {
        this.mobileLinY = mobileLinY;
    }

    public Float getMobileLinZ() {
        return mobileLinZ;
    }

    public void setMobileLinZ(Float mobileLinZ) {
        this.mobileLinZ = mobileLinZ;
    }

    public Float getMobileRotX() {
        return mobileRotX;
    }

    public void setMobileRotX(Float mobileRotX) {
        this.mobileRotX = mobileRotX;
    }

    public Float getMobileRotY() {
        return mobileRotY;
    }

    public void setMobileRotY(Float mobileRotY) {
        this.mobileRotY = mobileRotY;
    }

    public Float getMobileRotZ() {
        return mobileRotZ;
    }

    public void setMobileRotZ(Float mobileRotZ) {
        this.mobileRotZ = mobileRotZ;
    }

    public Float getMBarometer() {
        return mBarometer;
    }

    public void setMBarometer(Float mBarometer) {
        this.mBarometer = mBarometer;
    }

    public Integer getIceBeacon() {
        return iceBeacon;
    }

    public void setIceBeacon(Integer iceBeacon) {
        this.iceBeacon = iceBeacon;
    }

    public Integer getMintBeacon() {
        return mintBeacon;
    }

    public void setMintBeacon(Integer mintBeacon) {
        this.mintBeacon = mintBeacon;
    }

    public Integer getBbBeacon() {
        return bbBeacon;
    }

    public void setBbBeacon(Integer bbBeacon) {
        this.bbBeacon = bbBeacon;
    }

    public Integer getIceBeacon2() {
        return iceBeacon2;
    }

    public void setIceBeacon2(Integer iceBeacon2) {
        this.iceBeacon2 = iceBeacon2;
    }

    public Integer getMintBeacon2() {
        return mintBeacon2;
    }

    public void setMintBeacon2(Integer mintBeacon2) {
        this.mintBeacon2 = mintBeacon2;
    }

    public Integer getBbBeacon2() {
        return bbBeacon2;
    }

    public void setBbBeacon2(Integer bbBeacon2) {
        this.bbBeacon2 = bbBeacon2;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

}
