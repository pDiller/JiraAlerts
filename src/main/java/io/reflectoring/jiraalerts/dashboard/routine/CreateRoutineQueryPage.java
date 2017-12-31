package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.Optional;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.base.BasePage;

@AuthorizeInstantiation("administrator")
public class CreateRoutineQueryPage extends BasePage {

	public CreateRoutineQueryPage() {

		IModel<RoutineQueryDTO> routineQueryDTOModel = new Model<>(new RoutineQueryDTO());
		Form<RoutineQueryDTO> createRoutineForm = new Form<>("createRoutineForm", routineQueryDTOModel);

		createRoutineForm.add(new RoutineQueryPanel("routineQueryPanel", routineQueryDTOModel));

		createRoutineForm.add(new AjaxFallbackButton("submitButton", createRoutineForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateRoutineQueryPage.this));
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateRoutineQueryPage.this));
			}
		});

		add(createRoutineForm);
	}
}
