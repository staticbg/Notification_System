/**
 * Interface for notification sending channel. New channels should implement this.
 */

package com.notification.system.channel;

import com.notification.system.model.Message;

public interface Channel {

    /**
     * Name of the channel
     */
    String getName();

    /**
     * Method for sending notification via the channel
     */
    void sendNotification(Message message) throws Exception;
}
