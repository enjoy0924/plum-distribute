package com.plum.core.error;

import com.plum.core.i18n.I18NUtil;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * I18n'ed runtime exception thrown by Alfresco code.
 * 
 * @author gavinc
 */

public class PlumRuntimeException extends RuntimeException
{
    /**
     * Serial version UUID
     */
    private static final long serialVersionUID = 3787143176461219632L;

    private static final String MESSAGE_DELIMITER = " ";

    private String msgId;
    private transient Object[] msgParams = null;
    
    /**
     * Helper factory method making use of variable argument numbers
     */
    public static PlumRuntimeException create(String msgId, Object ...objects)
    {
        return new PlumRuntimeException(msgId, objects);
    }

    /**
     * Helper factory method making use of variable argument numbers
     */
    public static PlumRuntimeException create(Throwable cause, String msgId, Object ...objects)
    {
        return new PlumRuntimeException(msgId, objects, cause);
    }
    
    /**
     * Utility to convert a general Throwable to a RuntimeException.  No conversion is done if the
     * throwable is already a <tt>RuntimeException</tt>.
     * 
     * @see #create(Throwable, String, Object...)
     */
    public static RuntimeException makeRuntimeException(Throwable e, String msgId, Object ...objects)
    {
        if (e instanceof RuntimeException)
        {
            return (RuntimeException) e;
        }
        // Convert it
        return PlumRuntimeException.create(e, msgId, objects);
    }
    
    /**
     * Constructor
     * 
     * @param msgId     the message id
     */
    public PlumRuntimeException(String msgId)
    {
        super(resolveMessage(msgId, null));
        this.msgId = msgId;
    }
    
    /**
     * Constructor
     * 
     * @param msgId         the message id
     * @param msgParams     the message parameters
     */
    public PlumRuntimeException(String msgId, Object[] msgParams)
    {
        super(resolveMessage(msgId, msgParams));
        this.msgId = msgId;
        this.msgParams = msgParams;
    }

    /**
     * Constructor
     * 
     * @param msgId     the message id
     * @param cause     the exception cause
     */
    public PlumRuntimeException(String msgId, Throwable cause)
    {
        super(resolveMessage(msgId, null), cause);
        this.msgId = msgId;
    }
    
    /**
     * Constructor
     * 
     * @param msgId         the message id
     * @param msgParams     the message parameters
     * @param cause         the exception cause
     */
    public PlumRuntimeException(String msgId, Object[] msgParams, Throwable cause)
    {
        super(resolveMessage(msgId, msgParams), cause);
        this.msgId = msgId;
        this.msgParams = msgParams;
    }
    
    /**
     * @return the msgId
     */
    public String getMsgId()
    {
        return msgId;
    }

    /**
     * @return the msgParams
     */
    public Object[] getMsgParams()
    {
        return msgParams;
    }

    /**
     * @return the numericalId
     */
    public String getNumericalId()
    {
        return getMessage().split(MESSAGE_DELIMITER)[0];
    }

    /**
     * Resolves the message id to the localised string.
     * <p>
     * If a localised message can not be found then the message Id is
     * returned.
     * 
     * @param messageId     the message Id
     * @param params        message parameters
     * @return              the localised message (or the message id if none found)
     */
    private static String resolveMessage(String messageId, Object[] params)
    {
        String message = I18NUtil.getMessage(messageId, params);
        if (message == null)
        {
            // If a localised string cannot be found then return the messageId
            message = messageId;
        }
        return buildErrorLogNumber(message);
    }
    
    /**
     * Generate an error log number - based on MMDDXXXX - where M is month,
     * D is day and X is an atomic integer count.
     * 
     * @param message       Message to prepend the error log number to 
     * 
     * @return message with error log number prefix
     */
    private static String buildErrorLogNumber(String message)
    {
        // ensure message is not null
        if (message == null)
        {
            message= "";
        }
        
        Date today = new Date();
        StringBuilder buf = new StringBuilder(message.length() + 10);
        padInt(buf, today.getMonth(), 2);
        padInt(buf, today.getDate(), 2);
        padInt(buf, errorCounter.getAndIncrement(), 4);
        buf.append(MESSAGE_DELIMITER);
        buf.append(message);
        return buf.toString();
    }
    
    /**
     * Helper to zero pad a number to specified length 
     */
    private static void padInt(StringBuilder buffer, int value, int length)
    {
        String strValue = Integer.toString(value);
        for (int i = length - strValue.length(); i > 0; i--)
        {
            buffer.append('0');
        }
        buffer.append(strValue);
    }
    
    private static AtomicInteger errorCounter = new AtomicInteger();
    
    /**
     * Get the root cause.
     */
    public Throwable getRootCause()
    {
        Throwable cause = this;
        for (Throwable tmp = this; tmp != null ; tmp = cause.getCause())
        {
            cause = tmp;
        }
        return cause;
    }
}
