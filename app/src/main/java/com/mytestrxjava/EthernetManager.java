//package com.mytestrxjava;
//
//import android.content.Context;
//import android.os.INetworkManagementService;
//import android.os.RemoteException;
//import android.os.ServiceManager;
//import android.os.SystemProperties;
//
///**
// * Created by Yomoo on 2017/8/21.
// */
//
//public class EthernetManager {
//    private final Context mContext;
//    private INetworkManagementService mNMService;  //需要调用的服务
//
//    public EthernetManager(Context paramContext) {
//        this.mContext = paramContext;
//        this.mNMService = INetworkManagementService.Stub
//                .asInterface(ServiceManager.getService("network_management"));
//
////...........初始化一些对象 环境
//
//    }
//
//    public void setInterfaceUp(String paramString) { // 打开 服务
//
//        try {
//
//            this.mNMService.setInterfaceUp(paramString);
//
//            return;
//
//        } catch (RemoteException localRemoteException) {
//
//        }
//
//    }
//
//    public void setInterfaceDown(String paramString) { // 关闭 服务
//
//        try {
//
//            this.mNMService.setInterfaceDown(paramString);
//
//            return;
//
//        } catch (RemoteException localRemoteException) {
//
//        }
//
//    }
//
//
//
//
//    public void setMode(String paramString) { //设置模式 DHCP 还是 m
//
//        try {
//
//            this.mContext.getContentResolver();
//
//            if (this.DevName != null)
//
//                SystemProperties.set("net." + this.DevName[0] + ".mode",paramString);
//
//            return;
//
//        } finally {
//
//        }
//
//    }
//
//
//    public void updateDevInfo(EthernetDevInfo paramEthernetDevInfo)
//    { //修改数据 ip dns等等
//
////
//
////        EthernetDevInfo  上面 序列化 的类
//
//        try {
//
//            SystemProperties.set("net.dns1", paramEthernetDevInfo.getDns());
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".dns1", paramEthernetDevInfo.getDns());
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".dns2", "0.0.0.0");
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".config", "1");
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".mode", paramEthernetDevInfo.getDev_name());
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".ip", paramEthernetDevInfo.getIpaddr());
//
//            SystemProperties.set("net." + paramEthernetDevInfo.getDev_name()
//
//                    + ".gw", paramEthernetDevInfo.getRoute());
//
//            return;
//
//        } finally {
//
//        }
//
//    }
//}
