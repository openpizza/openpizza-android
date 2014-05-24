package de.flopska.android_rest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;

import com.example.test.R;

import de.flopska.android_rest.service.ContactsService;
import de.flopska.android_rest.service.data.ContactsRequest;
import de.flopska.android_rest.service.data.ContactsResponse;
import de.flopska.android_rest.service.restapi.RESTServiceCall;
import de.flopska.android_rest.service.restapi.RESTServiceHandler;

public class MainActivity extends Activity implements RESTServiceHandler<ContactsResponse> {

    private RESTServiceCall<ContactsRequest, ContactsResponse> service;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        Log.d("phone number", mPhoneNumber);
        
        List<String> numbers = new ArrayList<String>();
        numbers.add(mPhoneNumber);
        numbers.add("1234");
        
        ContactsRequest request = new ContactsRequest(mPhoneNumber, "123445", numbers);
        service = new ContactsService(this);
        service.httpPost(request, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void handleGetResponse(ContactsResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePostResponse(ContactsResponse response) {
		Log.d("handlePostResponse", response.toString());
	}
    
}
