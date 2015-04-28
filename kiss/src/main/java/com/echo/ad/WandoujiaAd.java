package com.echo.ad;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.wandoujia.ads.sdk.Ads;

/**
 * Created by jiangecho on 15/4/28.
 */
public class WandoujiaAd {
    public static void initAd(final Activity activity, final ViewGroup container, final AdOnLineConfig adOnLineConfig) {
        if (activity == null || adOnLineConfig == null) {
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    if (adOnLineConfig != null && (adOnLineConfig.showAd() || adOnLineConfig.showAppWallAd() || adOnLineConfig.showBannerAd())) {
                        Ads.init(activity, adOnLineConfig.getAppId(), adOnLineConfig.getSecretKey());
                    }
                    return true;
                } catch (Exception e) {
                    Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {

                if (success) {
                    /**
                     * pre load
                     */
                    if (adOnLineConfig != null && adOnLineConfig.showBannerAd() && container != null) {
                        Ads.preLoad(adOnLineConfig.getBannerAdTag(), Ads.AdFormat.banner);
                        /**
                         * add ad views
                         */
                        View bannerView = Ads.createBannerView(activity, adOnLineConfig.getBannerAdTag());
                        container.addView(bannerView, new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        ));

                    }

                    if (adOnLineConfig != null && adOnLineConfig.showAd()) {
                        Ads.preLoad(adOnLineConfig.getAdTag(), Ads.AdFormat.interstitial);
                    }

                    if (adOnLineConfig != null && adOnLineConfig.showAppWallAd()) {
                        Ads.preLoad(adOnLineConfig.getAppWallTag(), Ads.AdFormat.appwall);
                    }


                } else {
                    // init ad failed
                }
            }
        }.execute();
    }
}
