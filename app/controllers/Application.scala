package controllers

import play.api._
import data.Form
import data.Forms._
import libs.json.Json
import play.api.mvc._
import play.api.templates.Html
import views.html
import model.{SignUp, Login,User}

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Recomenda Ai - RAI"))
  }

  val loginForm = Form[Login](
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    ) (Login.apply)(Login.unapply)
      verifying ("Usuário ou senha inválidos", f => User.login(f)!=0)
  )


  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  def authenticate = Action {
    implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.login(formWithErrors)),
        login => {
              val token = User.getToken(login);
              Redirect("/login#user="+login.username+"#token="+token);
        }
      )
  }

  val signUpForm = Form[SignUp](
    mapping(
      "username"      -> nonEmptyText,
      "email"         -> nonEmptyText,
      "password"      -> nonEmptyText,
      "confirmedPass" -> nonEmptyText
    )(SignUp.apply)(SignUp.unapply)
      verifying ("A senha deve conter pelo menos 6 caracteres", f             => f.password.length >5)
      verifying ("A senha não confere", f                                     => f.confirmedPass==f.password)
      verifying ("Email ou username já cadastrado", f                         => User.check(f)==0)
  )

  def signUp = Action {
    Ok(html.cadastro(signUpForm))
  }

  def signupAction = Action {
    implicit request =>
                        signUpForm.bindFromRequest().fold(
                          errors  =>  { BadRequest(html.cadastro(errors)); },
                          signup  =>  {
                            User.signup(signup);

                            Redirect("/login").flashing(
                              "success" -> "Cadastro realizado com sucesso"
                            )
                          }
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