package ee.codehouse.userapps;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserApps extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getUserApps")) {
            this.getUserApps(callbackContext);
            return true;
        }
        return false;
    }

    private void getUserApps(CallbackContext callbackContext) {
        ArrayList<JSONObject> apps = new ArrayList();
        try {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            PackageManager packageManager = this.cordova.getActivity().getApplication().getPackageManager();
            for (ResolveInfo app: packageManager.queryIntentActivities( mainIntent, 0)) {
                HashMap<String, String> entry = new HashMap<>();
                entry.put("package", app.activityInfo.processName);
                entry.put("label", app.loadLabel(packageManager).toString());
                apps.add(new JSONObject(entry));
            }

            callbackContext.success(new JSONArray(apps));
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
    }
}
