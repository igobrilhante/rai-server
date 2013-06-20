package model

import anorm.Pk
import com.sun.xml.internal.bind.v2.TODO

/**
 *
 * @param id
 * @param usuario
 * @param poi
 * @param avaliacao
 */
case class Recomendacao(id: Pk[Long],usuario: Usuario,poi: Poi,avaliacao:Double)


object Recomendacao {

  def get(id : Long) = TODO

  def avaliarRecomendacao(id : Long) = {

  }

  def get(hourOfDay : Int, weather : String, lat : Double, lng : Double ) = TODO


}