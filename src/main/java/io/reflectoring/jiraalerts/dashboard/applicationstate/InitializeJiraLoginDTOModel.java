package io.reflectoring.jiraalerts.dashboard.applicationstate;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

import io.reflectoring.jiraalerts.jiraconnection.JiraConnection;
import io.reflectoring.jiraalerts.jiraconnection.JiraConnectionRepository;

/**
 * Loads the {@link JiraConnection} if existent and maps it to {@link JiraLoginDTO}. If its not existent, this model initializes new
 * {@link JiraLoginDTO}.
 */
public class InitializeJiraLoginDTOModel extends LoadableDetachableModel<JiraLoginDTO> {

	@Inject
	private JiraConnectionRepository jiraConnectionRepository;

	/**
	 * Constructor.
	 */
	public InitializeJiraLoginDTOModel() {
		Injector.get().inject(this);
	}

	@Override
	protected JiraLoginDTO load() {
		JiraLoginDTO jiraLoginDTO = new JiraLoginDTO();
		List<JiraConnection> jiraConnections = jiraConnectionRepository.findAll();

		if (jiraConnections.isEmpty()) {
			return jiraLoginDTO;
		} else {
			JiraConnection jiraConnection = jiraConnections.get(0);
			jiraLoginDTO.setUsername(jiraConnection.getUsername());
			jiraLoginDTO.setUrl(jiraConnection.getUrl());
			return jiraLoginDTO;
		}
	}
}
