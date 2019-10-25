package com.hy.binderdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hy.binder.ILoginInterface;

/**
 * @Name: BinderDemo
 * @Description: 描述信息
 * @Author: Created by heyong on 2019-10-24
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ILoginInterface.Stub() {
            @Override
            public void login() throws RemoteException {

            }

            @Override
            public void loginCallBack(boolean loginStatus, String user) throws RemoteException {
                Log.e("MyService", "loginCallBack loginStatus==>" + loginStatus + " user==>" + user);
            }

        };
    }
}
