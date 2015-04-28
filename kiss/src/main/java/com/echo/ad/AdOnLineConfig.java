package com.echo.ad;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by jiangecho on 15/4/28.
 */
public class AdOnLineConfig {
    private String appId;
    private String secretKey;
    private String showAd;
    private String showBannerAd;
    private String showAppWall;
    private String adTag; // 插屏
    private String bannerAdTag;
    private String appWallTag;

    private String autoBannerAd;
    private String autoBannerAdFreq;

    public AdOnLineConfig(String appId, String secretKey, String showAd, String showBannerAd,
                          String showAppWall, String adTag, String bannerAdTag, String appWallTag,
                          String autoBannerAd, String autoBannerAdFreq) {
        this.appId = appId;
        this.secretKey = secretKey;
        this.showAd = showAd;
        this.showBannerAd = showBannerAd;
        this.showAppWall = showAppWall;
        this.adTag = adTag;
        this.bannerAdTag = bannerAdTag;
        this.appWallTag = appWallTag;
        this.autoBannerAd = autoBannerAd;
        this.autoBannerAdFreq = autoBannerAdFreq;

    }

    public static AdOnLineConfig getOnLineConfig(Context context) {
        String appId = MobclickAgent.getConfigParams(context, "appId");
        String secretKey = MobclickAgent.getConfigParams(context, "secretKey");
        String showAd = MobclickAgent.getConfigParams(context, "showAd");
        String showBannerAd = MobclickAgent.getConfigParams(context, "showBannerAd");
        String showAppWall = MobclickAgent.getConfigParams(context, "showAppWall");
        String adTag = MobclickAgent.getConfigParams(context, "adTag");
        String bannerAdTag = MobclickAgent.getConfigParams(context, "bannerAdTag");
        String appWallTag = MobclickAgent.getConfigParams(context, "appWallTag");
        String autoBannerAd = MobclickAgent.getConfigParams(context, "autoBannerAd");
        String autoBannerAdFreq = MobclickAgent.getConfigParams(context, "autoBannerAdFreq");
        if (TextUtils.isEmpty(autoBannerAdFreq)){
            autoBannerAdFreq = "10";
        }

        return new AdOnLineConfig(appId, secretKey, showAd, showBannerAd, showAppWall,
                adTag, bannerAdTag, appWallTag, autoBannerAd, autoBannerAdFreq);
    }

    public boolean showAd(){
        if (showAd != null && showAd.equals("true")){
            return true;
        }
        return false;
    }

    public boolean showBannerAd(){
        if (showBannerAd != null && showBannerAd.equals("true")){
            return true;
        }
        return false;
    }

    public boolean showAppWallAd(){
        if (showAppWall != null && showAppWall.equals("true")){
            return true;
        }
        return false;
    }

    public boolean autoBannerAd(){
        if (autoBannerAd != null && autoBannerAd.equals("true")){
            return true;
        }
        return false;

        // TODO refactor to the following
//            if ("true".equals(autoBannerAd)){
//                return true;
//            }
    }

    public int getAutoBannerAdFreq(){
        return Integer.valueOf(autoBannerAdFreq);
    }

    public String getAppId() {
        return appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAdTag() {
        return adTag;
    }

    public String getBannerAdTag() {
        return bannerAdTag;
    }

    public String getAppWallTag() {
        return appWallTag;
    }
}
