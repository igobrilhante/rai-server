package model

import anorm._
import play.api.libs.json.{Json, JsValue, Writes}
import play.api.db.DB
import play.api.Play.current


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

  def evaluate(venueId : String,userId:Long, rating: Double) : Int = DB.withConnection {
    implicit conn =>
    val res = SQL("insert into fortaleza.recommendation(venue_id,user_id,rating) values({venue_id},{user_id},{rating})")
      .on("venue_id" -> venueId,
           "user_id"  -> userId,
           "rating"   -> rating).executeUpdate();
    return res
  }

  def get(hourOfDay : Int, weather : String, lat : Double, lng : Double ) : List[Recommendation] = {
    return null;
  }




}