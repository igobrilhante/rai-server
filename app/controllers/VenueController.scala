package controllers

import play.api.mvc._
import play.api.libs.json.Json
import model.{VenueTip, Venue}

/**
 * Created with IntelliJ IDEA.
 * User: igobrilhante
 * Date: 20/06/13
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
object VenueController extends Controller {

  def get(id : String) = Action{
    val venue :Venue = Venue.get(id)
    if(venue !=null){
      Ok(
        Json.obj(
          "result"->Json.obj(
            "venue"->  Json.toJson(venue)
          )
        )
      )
    }
    else{
         BadRequest(Json.obj(
           "result"->Json.obj()
           )
         )
    }
  }

  def comentarios(id : String) = Action{
    val tips : List[VenueTip] = Venue.comentarios(id)
    if(tips !=null){
      Ok(
        Json.obj(
          "result"->Json.obj(
                "count"   ->  tips.size,
                "tips"    ->  Json.toJson(tips)
          )
        )
      )
    }
    else{
      BadRequest(Json.obj(
        "result"->Json.obj()
      )
      )
    }
  }

  def getVenues(latitude : Double, longitude : Double) = Action{
    val venues = Venue.get(latitude,longitude);
    if(venues!=null){
      Ok(
        Json.obj(
          "result"->Json.obj(
            "count"   ->  venues.size,
            "venues"    ->  Json.toJson(venues)
          )
        )
      )
    }
    else{
      BadRequest(Json.obj(
        "result"->Json.obj()
      )
      )
    }
  }

  def buscar() = TODO

  def buscarPorCategorias(categories : String) = TODO
}
