package io.reflectoring.jiraalerts.notify;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import io.reflectoring.jiraalerts.appstate.ApplicationState;
import io.reflectoring.jiraalerts.appstate.ApplicationStateService;
import io.reflectoring.jiraalerts.device.Device;
import io.reflectoring.jiraalerts.device.DeviceRepository;
import io.reflectoring.jiraalerts.device.DeviceType;
import io.reflectoring.jiraalerts.iotaction.IoTAction;
import io.reflectoring.jiraalerts.iotaction.IoTActionRepository;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;
import io.reflectoring.jiraalerts.notify.raito4rpi.Raito4RPiNotifier;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 *
 */
@Service
public class NotifyDeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyDeviceService.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private IoTActionRepository iotActionRepository;

    @Autowired
    private JiraRestClientService jiraRestClientService;

    @Autowired
    private ApplicationStateService applicationStateService;

    public void notifyDevices() {

        if (ApplicationState.ACTIVE == applicationStateService.getApplicationState()) {

            List<Device> allDevices = deviceRepository.findAll();

            for (Device device : allDevices) {
                List<IoTAction> prioritizedIoTActions = iotActionRepository.findByDeviceOrderByPriorityFetchRoutineQuery(device.getId());
                String actionToNotify = getActionToNotify(prioritizedIoTActions);
                if(actionToNotify != null) {
                    boolean notificationSuccessfull = notifyDevice(device.getType(), actionToNotify);
                    if (notificationSuccessfull) {
                        String successMessage = MessageFormat.format("Notification for device {0} was successfull.", device.getName());
                        LOGGER.info(successMessage);
                    } else {
                        String failureMessage = MessageFormat.format("Notification for device {0} failed!", device.getName());
                        LOGGER.warn(failureMessage);
                    }
                } else {
                    LOGGER.info("No issue found, nothing happens");
                }
            }
        }
    }

    private String getActionToNotify(List<IoTAction> iotActions) {
        for (IoTAction iotAction : iotActions) {
            JiraRestClient initializedJiraRestClient = jiraRestClientService.getInitializedJiraRestClient();
            SearchResult searchResult = initializedJiraRestClient.getSearchClient().searchJql(iotAction.getRoutineQuery().getJql()).claim();
            if (searchResult.getTotal() > 0) {
                return iotAction.getAction();
            }
        }
        return null;
    }

    private boolean notifyDevice(DeviceType deviceType, String action) {
        final Notifier notifier;
        switch (deviceType) {
            case RAITO4RPI:
                notifier = new Raito4RPiNotifier();
                break;
            default:
                throw new NotImplementedException("This Feature is not implemented yet!");
        }
        return notifier.notify(action);
    }
}
