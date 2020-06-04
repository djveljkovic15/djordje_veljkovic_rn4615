package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource

interface RasporedRepository {

    fun fetchRaspored(): Observable<Resource<Unit>>

}