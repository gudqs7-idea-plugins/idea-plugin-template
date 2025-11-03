package cn.gudqs7.plugins.common.util.jetbrain;

import com.intellij.notification.*;

import static cn.gudqs7.plugins.consts.CommonIdConst.NOTIFICATION_ERROR_GROUP_ID;
import static cn.gudqs7.plugins.consts.CommonIdConst.NOTIFICATION_TIP_GROUP_ID;

/**
 * 通知提示工具类
 *
 * @author wenquan
 * @date 2022/6/2
 */
public class NotificationUtil {

    public static void showTips(String content) {
        showNotification(NOTIFICATION_TIP_GROUP_ID, content, NotificationType.INFORMATION);
    }

    public static void showError(String content) {
        showNotification(NOTIFICATION_ERROR_GROUP_ID, content, NotificationType.ERROR);
    }

    private static void showNotification(String groupId, String content, NotificationType notificationType) {
        NotificationGroup notificationGroup = NotificationGroupManager.getInstance().getNotificationGroup(groupId);
        Notification notification = notificationGroup.createNotification(content, notificationType);
        Notifications.Bus.notify(notification);
    }

}
