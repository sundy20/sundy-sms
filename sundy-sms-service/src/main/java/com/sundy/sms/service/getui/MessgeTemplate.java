package com.sundy.sms.service.getui;

import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

import java.util.Map;

/**
 * @version 1.0
 * @Description: 消息模版类
 * @CreateDate: 2017年7月13日
 */
public class MessgeTemplate {

    /**
     * 群发数据模版Android
     *
     * @param appId
     * @param appKey
     * @param title
     * @param text
     * @param url
     * @return
     */
    public static LinkTemplate getlinkTemplateAndroid(String appId, String appKey, String title, String text, String url) {

        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(url);

        return template;
    }

    /**
     * 群发数据模版IOS
     *
     * @param appId
     * @param appKey
     * @param title
     * @param text
     * @param url
     * @return
     */
    public static LinkTemplate getlinkTemplateIOS(String appId, String appKey, String title, String text, String url) {

        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        APNPayload payload = new APNPayload();
        // 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        // payload.setCategory("$由客户端定义");

        // 简单模式APNPayload.SimpleMsg
        // payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        // 字典模式使用APNPayload.DictionaryAlertMsg
        payload.setAlertMsg(getDictionaryAlertMsg(title, text));
        template.setAPNInfo(payload);
        // 设置打开的网址地址
        template.setUrl(url);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);

        template.setStyle(style);

        return template;
    }

    /**
     * 通知栏消息带透传数据模版Android
     *
     * @param appId
     * @param appKey
     * @param title
     * @param text
     * @param customContent
     * @return
     */
    public static NotificationTemplate getNotificationTemplateAndroid(String appId, String appKey, String title, String text, Map<String, Object> customContent) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        if (customContent != null && customContent.size() > 0) {
            String transmissionContent = JSON.toJSONString(customContent);
            template.setTransmissionContent(transmissionContent);
        }
        return template;
    }

    /**
     * 透传数据模版IOS
     *
     * @param appId
     * @param appKey
     * @param customContent
     * @return
     */
    public static TransmissionTemplate getTransmissionTemplateIOS(String appId, String appKey, String title, String text, Map<String, Object> customContent) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        APNPayload payload = new APNPayload();
        if (customContent != null && customContent.size() > 0) {
            String transmissionContent = JSON.toJSONString(customContent);
            template.setTransmissionContent(transmissionContent);
            payload.addCustomMsg("payload", transmissionContent);//将数据加入到APNS中
        }

        // 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        // payload.setCategory("$由客户端定义");

        // 简单模式APNPayload.SimpleMsg
        // payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        // 字典模式使用APNPayload.DictionaryAlertMsg
        payload.setAlertMsg(getDictionaryAlertMsg(title, text));
        // 添加多媒体资源
        // payload.addMultiMedia(new
        // MultiMedia().setResType(MultiMedia.MediaType.video)
        // .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
        // .setOnlyWifi(true));
        //
        template.setAPNInfo(payload);

        return template;
    }

    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title, String text) {
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody(text);
        // alertMsg.setActionLocKey("ActionLockey");
        // alertMsg.setLocKey("LocKey");
        // alertMsg.addLocArg("loc-args");
        // alertMsg.setLaunchImage("launch-image");
        // iOS8.2以上版本支持
        alertMsg.setTitle(title);
        // alertMsg.setTitleLocKey("TitleLocKey");
        // alertMsg.addTitleLocArg("TitleLocArg");
        return alertMsg;
    }

}
