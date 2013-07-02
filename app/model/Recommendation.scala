package model

import anorm.Pk
import play.api.libs.json.{Json, JsValue, Writes}


/**
 *
 * @param id
 * @param poi
 * @param avaliacao
 */
case class Recommendation(id: Pk[Long],poi: Venue,avaliacao:Double)

case class Score(id: Pk[Long],usuario : User, nota: Double)

object Recommendation {

  implicit val recomendacaoWrites = new Writes[Recommendation] {
    def writes(rec : Recommendation): JsValue = {
      Json.obj(
        "id"        -> rec.id.get,
        "poi"       -> rec.poi,
        "avaliacao" -> rec.avaliacao
      )
    }
  }

  implicit val notaWrites = new Writes[Score] {
    def writes(nota : Score): JsValue = {
      Json.obj(
        "id"            -> nota.id.get,
        "usuario"       -> nota.usuario,
        "nota"          -> nota.nota
      )
    }
  }

  def get(id : Long) : Recommendation = {
      return null;
  }

  def avaliarRecomendacao(id : Long,usuarioId:Long, nota: Double) = {

  }

  def get(hourOfDay : Int, weather : String, lat : Double, lng : Double ) : List[Recommendation] = {
    return null;
  }




}