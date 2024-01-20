package org.soralis_0912.esim_disabler;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedBridge;

public class Main implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!lpparam.packageName.equals("com.android.phone")) {
            return;
        }

        // for A14
        try {
            XposedHelpers.findAndHookMethod("com.android.phone.MiuiEsimManagerBase", lpparam.classLoader, "isRemoveEsimSwitch", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
        } catch (Throwable t) {
            XposedBridge.log("Failed to hook com.android.phone.MiuiEsimManagerBase method");
        }

        // for A13
        try {
            XposedHelpers.findAndHookMethod("com.android.phone.MiuiEsimManager", lpparam.classLoader, "isRemoveEsimSwitch", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
        } catch (Throwable t) {
            XposedBridge.log("Failed to hook com.android.phone.MiuiEsimManager method");
        }
        
    }
}
