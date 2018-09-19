package com.zxy.aop;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * @author：xinyu.zhou
 * @version: 2018/9/13
 * @ClassName:
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 */
public class AopApplication extends Application {

    private static AopApplication mApp;
    public Stack<Activity> store;

    public static AopApplication getAppContext() {
        return mApp;
    }

    public void onCreate() {
        super.onCreate();
        mApp = this;
        SharedPreferenceUtil.setContext(this);
        SharedPreferenceUtil.setLogin(AopApplication.getAppContext(), false);

        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity.getLocalClassName());
        }
    }


    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return store.lastElement();
    }

}
