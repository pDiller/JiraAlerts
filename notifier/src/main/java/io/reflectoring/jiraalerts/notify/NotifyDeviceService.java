package io.reflectoring.jiraalerts.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class NotifyDeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyDeviceService.class);

    public void notifyDevices() {
        LOGGER.info("Test");
    }
}
