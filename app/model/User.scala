package model

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.{Json, JsValue, Writes}

/**
 *
 * @param id
 * @param username
 * @param email
 * @param password
 */
case class User(id: Pk[Long] = NotAssigned,username: String, email: String, password: String)

/**
 *
 * @param username
 * @param password
 */
case class Login(username: String, password: String)

case class Session(username: String, email: String, password: String)

case class Token(usuario: User,token: String)

/**
 *
 */
object User {

  implicit val usuarioFormat = new Writes[User]{
       def writes(usuario : User) : JsValue = {
         Json.obj(
           "id"         -> usuario.id.get,
           "username"   -> usuario.username,
           "email"      -> usuario.email
         )
       }
  }

  def cadastrar(usuario: User) = {
//    DB.withConnection {
//      c =>
//      val res: Long = SQL("insert into smartcity.User(username,email,password) values ({username},{email},{password})")
//        .on(
//      "username" -> usuario.username,
//      "email" -> usuario.email,
//      "password" -> usuario.password
//      ).executeInsert()
//    }
    print("Casdastrar")
  }

  def login(usuario: User){

  }

  def editar(usuario: User){

  }

}

