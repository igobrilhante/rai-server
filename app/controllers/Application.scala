package controllers

import play.api._
import data.Form
import data.Forms._
import play.api.mvc._
import play.api.templates.Html
import views.html
import model.Login

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Recomenda Ai - RAI"))
  }

  val loginForm = Form[Login](
    mapping(
      "username" -> text,
      "password" -> text
    )(Login.apply)(Login.unapply)
  )

  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  def authenticate = Action {
    implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.login(formWithErrors)),
        user => Redirect(routes.Application.index)
      )
  }


//  def logout = Action {
//    Redirect(routes.Auth.login).withNewSession.flashing(
//      "success" -> "You are now logged out."                                            `
//    )
//  }
  
//  def main = Action {
//    Ok(views.html.main("Recomenda Ai"))
//  }
  
}