// ILoginInterface.aidl
package com.hy.binder;

// Declare any non-default types here with import statements

interface ILoginInterface {
    void login();
    void loginCallBack(boolean loginStatus,String user);
}
