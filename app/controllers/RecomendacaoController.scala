package controllers

import play.api.mvc._
import model.{Poi, Recomendacao}
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created with IntelliJ IDEA.
 * User: igobrilhante
 * Date: 20/06/13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
object RecomendacaoController extends Controller {


  def test() = Action(parse.json) {  request =>
    request.body.validate[String].map{
      case name => Ok("Hello " + name)
    }.recoverTotal{
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }

  }

  def get(id : Long) = Action{
    request =>
    {
      val rec: Recomendacao = Recomendacao.get(id)
      if(rec!=null){
      val data =     Json.obj(
        "resultado" -> Json.obj(
          "recomendacao"->Json.obj(
            "id"        -> rec.id.get,
            "poi"       -> Json.toJson(rec.poi),
            "avaliacao" -> rec.avaliacao
          )
        ),
        "meta" -> Json.obj(
          "code"->202
        )
      )

      Ok(data)
      }
      else{
        BadRequest(Json.obj(
          "resultado" -> Json.obj(),
          "meta" -> Json.obj("code"->401,"erro"->"Problema nao identificado")
        ))
      }
    }
  }



  def avaliar(id : Long,usuarioId:Long, avaliacao : Double) =  Action{

    request =>
    {
      val res = Recomendacao.avaliarRecomendacao(id,usuarioId,avaliacao)
      if(res != null){
        Ok(Json.obj(
          "resultado" -> Json.obj(),
          "meta"      -> Json.obj("code"->202)
        ))
      }
      BadRequest(Json.obj(
        "resultado" -> Json.obj(),
        "meta" -> Json.obj("code"->401,"erro"->"Problema nao identificado")
      ))

    }

  }

  def contexto(hourOfDay : Int, weather : String, lat : Double, lng : Double ) = Action{
    implicit request =>
    {
      val recs:List[Recomendacao] = Recomendacao.get(hourOfDay,weather,lat,lng)

      if (recs!=null){
      Ok(Json.obj(
        "resultado" -> Json.obj(
            "count" ->  recs.size,
            "recs"  ->  Json.arr(recs.map(rec => Json.toJson(rec)))
        ),
        "meta" -> Json.obj("code"->202)
      ))
      }
      BadRequest(Json.obj(
        "resultado" -> Json.obj(),
        "meta"      -> Json.obj("code"->401,"erro"->"Problema nao identificado")
      ))
    }
  }

}
