package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedResponse

interface RasporedService {

    @GET("raspored/json.php")
    fun findAll(): Observable<List<RasporedResponse>>

}