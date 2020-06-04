package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories

import io.reactivex.Observable
import retrofit2.HttpException
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.remote.RasporedService
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Raspored
import timber.log.Timber

class RasporedRepositoryImpl(
    private val raporedService: RasporedService
) : RasporedRepository {

    override fun getRaspored(): Observable<List<Raspored>> {
        Timber.e("I LOVE IT")

        val r = raporedService
            .findAll()
            .doOnEach {
                Timber.e("A")
            }
            .doOnNext { Timber.e("B") }
            .doOnError {
                Timber.e("KURCINAAAAAAAA")
            }
            .map {
                Timber.e("KURIN")
                Timber.e("$it")
                it.map {
                    Timber.e(it.predmet)
                    Raspored(it.predmet, it.tip, it.nastavnik, it.grupe, it.dan, it.termin, it.ucionica)
                }
            }

        Timber.e(r.take(0).take(0).toString())
        Timber.e(r.take(0).toString())
        Timber.e("$r")
        return r;
    }
}