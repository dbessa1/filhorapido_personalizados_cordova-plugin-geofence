public void startApplication(String packageName)
{
    try
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo info : resolveInfoList)
            if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
            {
                launchComponent(info.activityInfo.packageName, info.activityInfo.name);
                return;
            }

        // No match, so application is not installed
        showInMarket(packageName);
    }
    catch (Exception e) 
    {
        showInMarket(packageName);
    }
}

private void launchComponent(String packageName, String name)
{
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.addCategory("android.intent.category.LAUNCHER");
    intent.setComponent(new ComponentName(packageName, name));
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    startActivity(intent);
}

private void showInMarket(String packageName)
{
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
}





public class TransitionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String error = intent.getStringExtra("error");

        if (error != null) {
            //handle error
            Log.println(Log.ERROR, "YourAppTAG", error);
        } else {
			
       startApplication("com.grantec.filhorapido");
	  
        }
    }
}
