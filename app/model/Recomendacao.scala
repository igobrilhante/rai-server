package model

import anorm.Pk

/**
 *
 * @param id
 * @param usuario
 * @param poi
 * @param avaliacao
 */
case class Recomendacao(id: Pk[Long],usuario: Usuario,poi: Poi,avaliacao:Double)


object Recomendacao {

  def get(usuario: Usuario, k: Int) : List[Recomendacao] = {

     return null;
  }

  def avaliarRecomendacao(id : Long) = {

  }


}