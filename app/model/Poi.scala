package model

import anorm.{NotAssigned, Pk}
import java.sql.Timestamp
import play.api.libs.json._
import play.api.libs.json

/**
 *
 * @param id
 * @param nome
 * @param latitude
 * @param longitude
 * @param avaliacao
 * @param categorias
 */
case class Poi(
                id : Pk[String] = NotAssigned,
                nome: String,
                latitude:Double,
                longitude:Double,
                avaliacao:Double,
                categorias: Set[String]
                )

/**
 *
 * @param poi
 * @param createdAt
 * @param comentario
 */
case class PoiComentario(poi: Poi, createdAt: Timestamp,comentario: String)


object Poi {

  implicit val poiWrites = new Writes[Poi] {
    def writes(poi: Poi): JsValue = {
      Json.obj(
        "id"          -> poi.id.get,
        "nome"        -> poi.nome,
        "latitude"    -> poi.latitude,
        "longitude"   -> poi.longitude,
        "avaliacao"   -> poi.avaliacao,
        "categorieas" -> Json.toJson(poi.categorias)
      )
    }
  }

  def get(id: Long) : Poi = {
    return null;
  }

  def comentarios(id: Long) : List[PoiComentario] = {
    return null;
  }

}


