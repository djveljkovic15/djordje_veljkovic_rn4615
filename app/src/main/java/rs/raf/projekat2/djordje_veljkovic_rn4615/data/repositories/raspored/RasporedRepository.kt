package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.raspored

import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored

interface RasporedRepository {

    fun fetchRaspored(): Observable<Resource<Unit>>

    fun getRaspored(): Observable<List<Raspored>>

    fun filterRaspored(filter:String, grupa:String, dan:String): Observable<List<Raspored>>
}