package com.hy.binderdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hy.binder.ILoginInterface;

public class MainActivity extends AppCompatActivity {
    private ILoginInterface iLogin;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iLogin = ILoginInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private boolean isStartRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBinderService();
    }

    private void initBinderService() {
        Intent intent = new Intent();
        intent.setAction("hy.binder.b");
        intent.setPackage("com.hy.binder.b");
        bindService(intent, conn, BIND_AUTO_CREATE);
        isStartRemote=true;
    }

    public void jumpBinderDemoB(View view) {
        try {
            if (iLogin != null) {
                iLogin.login();
            } else {
                Toast.makeText(this, "请安装B应用...", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isStartRemote) {
            // 解绑服务，一定要记得解绑服务，否则可能会报异常(服务连接资源异常)
            unbindService(conn);
        }
    }
}
