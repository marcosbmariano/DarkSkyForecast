package com.mark.darkskyforecast.adapters;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * Created by mark on 01/03/16.
 */
public class MySyncAdapter extends AbstractThreadedSyncAdapter {
    private ContentResolver mContentResolver;

    public MySyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    /*
        for back compatibility with android 3.0
     */
    public MySyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs){
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {

    }
}
