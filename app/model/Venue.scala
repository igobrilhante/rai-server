package model

import anorm.{NotAssigned, Pk}
import java.sql.Timestamp
import play.api.libs.json._
import anorm._
import play.api.db.DB
import anorm.SqlParser._
import play.api.Play.current

/**
 *
 * @param id
 * @param name
 * @param latitude
 * @param longitude
 * @param rating
 * @param categories
 */
case class Venue(
                id : Pk[String] = NotAssigned,
                name: String,
                latitude:Double,
                longitude:Double,
                rating:Double,
                categories: Set[String]
                )

case class VenueTip(id: Pk[String]=NotAssigned,venueId: String,userId: Long, createdAt: Long,text: String)

object VenueTip{
  implicit val tipWrites = new Writes[VenueTip] {
    def writes(tip: VenueTip): JsValue = {
      Json.obj(
        "id"          -> tip.id.get,
        "venueId"     -> tip.venueId,
        "userId"      -> tip.userId,
        "createdAt"   -> tip.createdAt,
        "text"        -> tip.text
      )
    }
  }

  val tipSQL = {
    getAliased[Pk[String]]("tip_id")~
      getAliased[String]("venue_id")~
      getAliased[Long]("user_id")~
      getAliased[Long]("datetime")~
      getAliased[String]("tip_content") map {
      case tipId~venueId~userId~datetime~content => VenueTip(tipId,venueId,userId,datetime,content)
    }
  }
}

object Venue {

  implicit val poiWrites = new Writes[Venue] {
    def writes(poi: Venue): JsValue = {
      Json.obj(
        "id"          -> poi.id.get,
        "nome"        -> poi.name,
        "latitude"    -> poi.latitude,
        "longitude"   -> poi.longitude,
        "avaliacao"   -> poi.rating,
        "categories" -> Json.toJson(poi.categories)
      )
    }
  }

  val venueSQL = {
    getAliased[Pk[String]]("id")~
    getAliased[String]("name") ~
    getAliased[Double]("latitude")~
    getAliased[Double]("longitude")~
    getAliased[Double]("rating") map {
    case id~name~latitude~longitude~rating => Venue(id,name,latitude,longitude,rating,Set())
    }
  }

  def get(latitude : Double, longitude : Double) : List[Venue]  = DB.withConnection {
    implicit conn =>
      SQL(
        "select * from fortaleza.venue where st_dwithin(st_geomfromtext('POINT('||{longitude}||' '||{latitude}||')',4326),geom,{distance}) limit 3;"
      ).on("latitude" -> latitude,"longitude" -> longitude,"distance" -> 0.008).as{venueSQL *}
  }

  def get(id: String) : Venue = DB.withConnection {
      implicit conn =>
            SQL(
            "select * from fortaleza.venue limit 1;"
            ).on("id" -> id).as{venueSQL.single}
  }


  def comentarios(id: String) : List[VenueTip] = DB.withConnection {
    implicit conn =>
      SQL(
        "select * from fortaleza.venue_tip where venue_id = {venue_id} order by datetime desc limit 5"
      ).on("venue_id" -> id).as{VenueTip.tipSQL *}
  }

}


