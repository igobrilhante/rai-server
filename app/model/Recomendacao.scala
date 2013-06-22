package model

import anorm.Pk
import play.api.libs.json.{Json, JsValue, Writes}


/**
 *
 * @param id
 * @param poi
 * @param avaliacao
 */
case class Recomendacao(id: Pk[Long],poi: Poi,avaliacao:Double)

case class Nota(id: Pk[Long],usuario : Usuario, nota: Double)

object Recomendacao {

  implicit val recomendacaoWrites = new Writes[Recomendacao] {
    def writes(rec : Recomendacao): JsValue = {
      Json.obj(
        "id"        -> rec.id.get,
        "poi"       -> rec.poi,
        "avaliacao" -> rec.avaliacao
      )
    }
  }

  implicit val notaWrites = new Writes[Nota] {
    def writes(nota : Nota): JsValue = {
      Json.obj(
        "id"            -> nota.id.get,
        "usuario"       -> nota.usuario,
        "nota"          -> nota.nota
      )
    }
  }

  def get(id : Long) : Recomendacao = {
      return null;
  }

  def avaliarRecomendacao(id : Long,usuarioId:Long, nota: Double) = {

  }

  def get(hourOfDay : Int, weather : String, lat : Double, lng : Double ) : List[Recomendacao] = {
    return null;
  }


}