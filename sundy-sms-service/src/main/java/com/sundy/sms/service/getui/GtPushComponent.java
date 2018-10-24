package com.sundy.sms.service.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.sundy.sms.service.config.GetuiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 个推工具类
 * @CreateDate: 2017年7月12日
 */
@Component
public class GtPushComponent {

    private static final Logger logger = LoggerFactory.getLogger(GtPushComponent.class);

    @Autowired
    private GetuiProperties getuiProperties;

    /**
     * 推送所有用户Android
     *
     * @param title 标题
     * @param text  内容
     * @param url   链接
     * @return IPushResult
     */
    public IPushResult pushMessageToAllAndroid(String title, String text, String url) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        AppMessage toAppmessage = AppPushMessage.getAppMessageAllAndroid(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, url);
        IPushResult ret = push.pushMessageToApp(toAppmessage);
        if (ret != null) {

            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToAllAndroid messge fail");
        }
        return ret;

    }

    /**
     * 推送所有用户IOS
     *
     * @param title 标题
     * @param text  内容
     * @param url   链接
     * @return IPushResult
     */
    public IPushResult pushMessageToAllIOS(String title, String text, String url) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        AppMessage toAppmessage = AppPushMessage.getAppMessageAllIOS(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, url);
        IPushResult ret = push.pushMessageToApp(toAppmessage);
        if (ret != null) {

            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToAllIOS messge fail");
        }
        return ret;

    }

    /**
     * 推送指定用户Android
     *
     * @param cid
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToSingleAndroid(String cid, String title, String text, Map<String, Object> customContent) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        SingleMessage message = AppPushMessage.getAppMessageSingleAndroid(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);

        IPushResult ret;
        Target target = new Target();
        target.setAppId(getuiProperties.getAppId());
        target.setClientId(cid);
        try {
            ret = push.pushMessageToSingle(message, target);
            logger.info(ret.getResponse().toString());
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("pushMessageToSingleAndroid messge fail", e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());

        }
        return ret;

    }

    /**
     * 对指定用户推送消息IOS
     *
     * @param cid
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToSingleIOS(String cid, String title, String text, Map<String, Object> customContent) {

        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        SingleMessage message = AppPushMessage.getAppMessageSingleIOS(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);
        IPushResult ret = null;
        // 配置推送目标
        Target target = new Target();
        target.setAppId(getuiProperties.getAppId());
        target.setClientId(cid);
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("消息服务器响应异常", e.getMessage());
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToSingleIOS messge fail");
        }
        return ret;

    }


    /**
     * 推送指定别名用户Android
     *
     * @param alias
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToSingleAliasAndroid(String alias, String title, String text, Map<String, Object> customContent) {
        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        SingleMessage message = AppPushMessage.getAppMessageSingleAndroid(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);

        IPushResult ret = null;
        Target target = new Target();
        target.setAppId(getuiProperties.getAppId());
        target.setAlias(alias);
        try {
            ret = push.pushMessageToSingle(message, target);
            logger.info(ret.getResponse().toString());
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("pushMessageToSingleAndroid messge fail", e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());

        }
        return ret;

    }

    /**
     * 对指定别名用户推送消息IOS
     *
     * @param alias
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToSingleAliasIOS(String alias, String title, String text, Map<String, Object> customContent) {

        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        SingleMessage message = AppPushMessage.getAppMessageSingleIOS(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);
        IPushResult ret = null;
        // 配置推送目标
        Target target = new Target();
        target.setAppId(getuiProperties.getAppId());
        target.setAlias(alias);
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            logger.error("消息服务器响应异常", e.getMessage());
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToSingleIOS messge fail");
        }
        return ret;

    }

    /**
     * 对指定列表用户推送消息Android
     *
     * @param cids
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToListAndroid(List<String> cids, String title, String text, Map<String, Object> customContent) {

        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        ListMessage message = AppPushMessage.getAppMessageListAndroid(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);
        // 配置推送目标
        List<Target> targets = new ArrayList<Target>();
        for (String cid : cids) {
            Target target = new Target();
            target.setAppId(getuiProperties.getAppId());
            target.setClientId(cid);
            targets.add(target);
        }
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToListAndroid messge fail");
        }
        return ret;

    }

    /**
     * 对指定列表用户推送消息IOS
     *
     * @param cids
     * @param title         标题
     * @param text          内容
     * @param customContent 透传信息
     * @return IPushResult
     */
    public IPushResult pushMessageToListIOS(List<String> cids, String title, String text, Map<String, Object> customContent) {

        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
        // IGtPush push = new IGtPush(host, appkey, master);
        // host为域名，根据域名区分是http协议/https协议
        IGtPush push = new IGtPush(getuiProperties.getAppKey(), getuiProperties.getMasterSecret(), true);
        ListMessage message = AppPushMessage.getAppMessageListIOS(getuiProperties.getAppId(), getuiProperties.getAppKey(), title, text, customContent);
        // 配置推送目标
        List<Target> targets = new ArrayList<Target>();
        for (String cid : cids) {
            Target target = new Target();
            target.setAppId(getuiProperties.getAppId());
            target.setClientId(cid);
            targets.add(target);
        }
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        if (ret != null) {
            logger.info(ret.getResponse().toString());
        } else {
            logger.error("消息服务器异常  pushMessageToListIOS messge fail");
        }
        return ret;

    }


}
