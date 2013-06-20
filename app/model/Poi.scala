package model

import anorm.{NotAssigned, Pk}
import java.sql.Timestamp

/**
 *
 * @param id
 * @param nome
 * @param latitude
 * @param Longitude
 * @param avaliacao
 * @param categorias
 */
case class Poi(
                id : Pk[Long] = NotAssigned,
                nome: String,
                latitude:Double,
                Longitude:Double,
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
  def get(id: Long) : Poi = {
    return null;
  }

  def comentarios(id: Long) : List[PoiComentario] = {
    return null;
  }

}


