package controllers

import play.api.data.Form
import play.api.data.Forms._
import anorm.{Pk, NotAssigned}
import model.{SignUp, User}
import play.api.mvc._
import views.html

/**
 * Created with IntelliJ IDEA.
 * User: igobrilhante
 * Date: 18/06/13
 * Time: 02:04
 * To change this template use File | Settings | File Templates.
 */
object UserController extends Controller {


  val usuarioForm = Form[User](
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "username" -> text,
      "email" -> email,
      "password" -> text
    )(User.apply)(User.unapply)
  )



  def get(id: Long) = TODO

  def recomendacao(id : Long) = TODO

  def recomendacao(id : Long, categories : List[String]) = TODO
}
