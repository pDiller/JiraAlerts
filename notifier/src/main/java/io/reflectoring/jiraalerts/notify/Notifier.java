package io.reflectoring.jiraalerts.notify;

/**
 * Interface for calling an action on the IoT-Device.
 */
public interface Notifier {

    /**
     * notifies the IoT-Device with a given json.
     *
     * @param iotActionAsJson the iot action formatted in json.
     * @return <code>true</code> when the notification worked. Otherwise <code>false</code>.
     */
    boolean notify(String iotActionAsJson);

}
