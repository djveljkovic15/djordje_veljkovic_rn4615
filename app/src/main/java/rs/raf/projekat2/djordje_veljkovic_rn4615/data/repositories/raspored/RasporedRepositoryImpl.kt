package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.raspored

import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.raspored.RasporedDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.remote.RasporedService
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedEntity

class RasporedRepositoryImpl(
    private val rasporedService: RasporedService,
    private val rasporedDao: RasporedDao
) : RasporedRepository {

    override fun fetchRaspored(): Observable<Resource<Unit>> {
        return rasporedService.findAll()
            .doOnNext{
                val entities = it.map { response ->
                    RasporedEntity(
//                        Random.nextInt(1,99999),  // Mora da postoji bolji nacin???????
                        response.predmet,
                        response.tip,
                        response.nastavnik,
                        response.grupe,
                        response.dan,
                        response.termin,
                        response.ucionica
                    )
                }
                rasporedDao.deleteAndInsertAll(entities)
            }.map {
                Resource.Success(Unit)
            }
    }

    override fun getRaspored(): Observable<List<Raspored>> {
        return rasporedDao.findAll()
            .map {
                it.map { entity ->
                    Raspored(
                        entity.id,
                        entity.predmet,
                        entity.tip,
                        entity.nastavnik,
                        entity.grupe,
                        entity.dan,
                        entity.termin,
                        entity.ucionica
                    )
                }
            }
    }

    override fun filterRaspored(filter: String, grupa: String, dan: String):Observable<List<Raspored>> {
        return rasporedDao
            .filterRaspored(filter,grupa,dan)
            .map {
                it.map{ ti ->
                    Raspored(
                        ti.id,
                        ti.predmet,
                        ti.tip,
                        ti.nastavnik,
                        ti.grupe,
                        ti.dan,
                        ti.termin,
                        ti.ucionica
                    )
                }
            }
    }
}
