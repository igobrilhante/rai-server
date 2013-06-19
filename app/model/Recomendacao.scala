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