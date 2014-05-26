package de.openpizza.android.ordermodul;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

public class OrderSyncAdapter extends AbstractThreadedSyncAdapter {
	// Global variables
	// Define a variable to contain a content resolver instance
	ContentResolver mContentResolver;

	/**
	 * Set up the sync adapter
	 */
	public OrderSyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		/*
		 * If your app uses a content resolver, get an instance of it from the
		 * incoming Context
		 */
		mContentResolver = context.getContentResolver();
	}

	/**
	 * Set up the sync adapter. This form of the constructor maintains
	 * compatibility with Android 3.0 and later platform versions
	 */
	public OrderSyncAdapter(Context context, boolean autoInitialize,
			boolean allowParallelSyncs) {
		super(context, autoInitialize, allowParallelSyncs);
	}

	@Override
	public void onPerformSync(Account account, Bundle bundle, String _2,
			ContentProviderClient _3, SyncResult syncResult) {

		Log.d("Test", "onPerformSync");
	}


}