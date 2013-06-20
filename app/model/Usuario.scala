package model

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

/**
 *
 * @param id
 * @param username
 * @param email
 * @param password
 */
case class Usuario(id: Pk[Long] = NotAssigned,username: String, email: String, password: String)

/**
 *
 * @param username
 * @param password
 */
case class LoginUsuario(username: String, password: String)

case class SessaoUsuario(username: String, email: String, password: String)

case class TokenUsuario(usuario: Usuario,token: String)

/**
 *
 */
object Usuario {

  def cadastrar(usuario: Usuario) = {
//    DB.withConnection {
//      c =>
//      val res: Long = SQL("insert into smartcity.Usuario(username,email,password) values ({username},{email},{password})")
//        .on(
//      "username" -> usuario.username,
//      "email" -> usuario.email,
//      "password" -> usuario.password
//      ).executeInsert()
//    }
    print("Casdastrar")
  }
}

