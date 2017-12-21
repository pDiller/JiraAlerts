package io.reflectoring.jiraalerts.home;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import io.reflectoring.jiraalerts.base.BasePage;

@AuthorizeInstantiation("administrator")
public class HomePage extends BasePage {

}
