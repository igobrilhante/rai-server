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

case class SignUp(username: String, email: String,  password: String, confirmedPass: String)

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

  def signup(usuario: SignUp) : Unit = DB.withConnection {
      implicit conn =>
      val res = SQL("insert into fortaleza.User(username,email,password,token) values ({username},{email},{password},{token}) ")
        .on(
      "username"  -> usuario.username,
      "email"     -> usuario.email,
      "password"  -> usuario.password,
      "token"     -> generateToken()
      ).executeUpdate();
      print("opaaa")
  }

  def generateToken(): String = {
      val token = "token";
      return token;
  }

  def check(data: SignUp) : Long = DB.withConnection {
    implicit conn =>
      val res = SQL("select count(*) from fortaleza.user where email = {email} or username = {username}")
                .on("email"     -> data.email,
                    "username" -> data.username).apply().head
      val count = res[Long]("count");

      return count;
    }

  def login(login: Login): Long = DB.withConnection {
    implicit conn =>
      val res = SQL("select count(*) from fortaleza.user where username = {username} and password = {password}")
        .on("username"    -> login.username,
            "password"    -> login.password).apply().head
      val count = res[Long]("count");
      print(count)
      return count;
  }

  def getToken(login: Login):String = DB.withConnection {
    implicit conn =>
      val res = SQL("select token from fortaleza.user where username = {username} and password = {password}")
        .on("username"    -> login.username,
            "password"    -> login.password).apply().head
      val token = res[String]("token");
      return token;
  }


}

