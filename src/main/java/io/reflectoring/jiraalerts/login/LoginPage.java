package io.reflectoring.jiraalerts.login;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import java.util.Optional;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.common.FeedbackPanelErrorClassModifier;
import io.reflectoring.jiraalerts.common.FormComponentErrorClassModifier;
import io.reflectoring.jiraalerts.common.PlaceholderAttributeModifier;
import io.reflectoring.jiraalerts.home.HomePage;

public class LoginPage extends BasePage {

	public LoginPage() {

		FencedFeedbackPanel loginFeedbackPanel = new FencedFeedbackPanel("loginFeedbackPanel", this);
		loginFeedbackPanel.add(new FeedbackPanelErrorClassModifier());
		add(loginFeedbackPanel);

		Form<LoginDTO> loginForm = new Form<>("loginForm", Model.of(new LoginDTO()));

		addUsernameComponents(loginForm);

		addPasswordComponents(loginForm);

		loginForm.add(new AjaxFallbackButton("loginButton", new ResourceModel("login.button.text"), loginForm) {

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(LoginPage.this));
			}

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> target) {
				super.onSubmit(target);

				LoginDTO loginDTO = loginForm.getModelObject();
				String username = loginDTO.getUsername().toLowerCase();
				String password = loginDTO.getPassword().toLowerCase();

				login(username, password, target);
			}
		});

		add(loginForm);
	}

	private void login(String username, String password, Optional<AjaxRequestTarget> targetOptional) {
		try {
			AuthenticatedWebSession.get().signIn(username, password);
			setResponsePage(HomePage.class);
		} catch (UserNotLoggedInException userNotLoggedInException) {
			error(getString("login.failed.text"));
			targetOptional.ifPresent(target -> target.add(LoginPage.this));
		}
	}

	private void addUsernameComponents(Form<LoginDTO> loginForm) {
		IModel<String> usernameLabelModel = new ResourceModel("login.username.label");
		IModel<String> usernameModel = model(from(LoginDTO.class).getUsername()).bind(loginForm.getModel());

		TextField<String> usernameInput = new TextField<>("usernameInput", usernameModel);
		usernameInput.setLabel(usernameLabelModel);
		usernameInput.setRequired(true);
		usernameInput.add(new PlaceholderAttributeModifier(usernameLabelModel));
		usernameInput.add(new FormComponentErrorClassModifier());

		SimpleFormComponentLabel usernameLabel = new SimpleFormComponentLabel("usernameLabel", usernameInput);

		FeedbackPanel usernameFeedbackPanel = new FencedFeedbackPanel("usernameFeedbackPanel", usernameInput);
		usernameFeedbackPanel.add(new FeedbackPanelErrorClassModifier());

		loginForm.add(usernameInput, usernameLabel, usernameFeedbackPanel);
	}

	private void addPasswordComponents(Form<LoginDTO> loginForm) {
		IModel<String> passwordLabelModel = new ResourceModel("login.password.label");
		IModel<String> passwordModel = model(from(LoginDTO.class).getPassword()).bind(loginForm.getModel());

		TextField<String> passwordInput = new PasswordTextField("passwordInput", passwordModel);
		passwordInput.setLabel(passwordLabelModel);
		passwordInput.add(new PlaceholderAttributeModifier(passwordLabelModel));
		passwordInput.add(new FormComponentErrorClassModifier());

		SimpleFormComponentLabel passwordLabel = new SimpleFormComponentLabel("passwordLabel", passwordInput);

		FeedbackPanel passwordFeedbackPanel = new FencedFeedbackPanel("passwordFeedbackPanel", passwordInput);
		passwordFeedbackPanel.add(new FeedbackPanelErrorClassModifier());

		loginForm.add(passwordInput, passwordLabel, passwordFeedbackPanel);
	}
}
