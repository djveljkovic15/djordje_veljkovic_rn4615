package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories

import io.reactivex.Observable
import retrofit2.HttpException
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.RasporedDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.remote.RasporedService
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Raspored
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.RasporedEntity
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource
import timber.log.Timber

class RasporedRepositoryImpl(
    private val rasporedService: RasporedService,
    private val rasporedDao: RasporedDao
) : RasporedRepository {

    override fun fetchRaspored(): Observable<Resource<Unit>> {
        return rasporedService.findAll()
            .doOnNext{
                val entities = it.map {
                    RasporedEntity(
                        it.id,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica
                    )
                }
                rasporedDao.deleteAndInsertAll(entities)
            }.map {
                Resource.Success(Unit)
            }
    }
}
