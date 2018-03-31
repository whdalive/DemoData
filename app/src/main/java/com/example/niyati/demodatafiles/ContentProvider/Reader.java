package com.example.niyati.demodatafiles.ContentProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niyati.demodatafiles.R;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Fragment {

    ArrayAdapter<String> mAdapter;
    List<String> contactsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        ListView contactView = view.findViewById(R.id.contact_view);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,contactsList);
        contactView.setAdapter(mAdapter);
        switch (getArguments().getInt("key")){
            case 1:
                contactsList.clear();
                mAdapter.notifyDataSetChanged();
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CONTACTS},666);
                }else {
                    readContacts();
                }
                break;
            case 2:
                contactsList.clear();
                mAdapter.notifyDataSetChanged();
                readProvider();
                break;
        }

        return view;
    }

    private void readProvider() {

        Uri uri_player = Uri.parse("content://com.example.niyati.myprovider/player");

        ContentValues values = new ContentValues();
        values.put("name","yaoming");

        ContentResolver resolver = getActivity().getContentResolver();
        resolver.insert(uri_player,values);

        Cursor cursor = resolver.query(uri_player,new String[]{"_id","name"},null,null,null,null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                contactsList.add(id + "\n" + name);
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    private void readContacts() {

        Cursor cursor = null;
        try {

            cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if (cursor!=null){

                while (cursor.moveToNext()){
                    String displaynName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displaynName+ "\n" + number);
                }
                mAdapter.notifyDataSetChanged();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            if (cursor !=null){{
                cursor.close();
            }}
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 666:
                if (grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    Toast.makeText(getActivity(),"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }
}
