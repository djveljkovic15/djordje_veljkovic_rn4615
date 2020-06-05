package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored

data class RasporedResponse(
    val id: Long,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)