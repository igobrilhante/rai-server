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

  def evaluate(venueId : String,user:String, rating: Double, time: Long) : Int = DB.withConnection {
    implicit conn =>
    val res = SQL("insert into fortaleza.recommendation(venue_id,user_id,rating,time) values({venue_id},(select id from fortaleza.user where username = {username}),{rating},{time})")
      .on("venue_id"  -> venueId,
           "username" -> user,
           "rating"   -> rating,
           "time"     -> time).executeUpdate();
    return res
  }

  def get(dow : Int, lat : Double, lng : Double ) : List[Venue] = DB.withConnection {
    implicit conn =>
    val res =  SQL(
        "   select *," +
          " st_distance(ST_Transform(st_geomfromtext('POINT('||{longitude}||' '||{latitude}||')',4326),2163),ST_Transform(geom,2163))::integer as distance, rec " +
          " from fortaleza.venue v, fortaleza.page_rank p " +
          " where st_dwithin(st_geomfromtext('POINT('||{longitude}||' '||{latitude}||')',4326),geom,{distance}) " +
          " and v.id = p.venue_id and dow={dow}" +
          " order by rec desc" +
          " limit 10;"
      ).on(
        "latitude" -> lat,
        "longitude" -> lng,
        "dow" -> dow,
        "distance" -> 0.01)
        .as{Venue.venueSQL *}

      return res;
  }




}