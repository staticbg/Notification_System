/**
 * Message Status enum used for tracking the status of the message and retry sending
 * when needed (i.e. on FAILED)
 */
package com.notification.system.model;


public enum MessageStatus {
    NEW, FAILED, SENT
}
