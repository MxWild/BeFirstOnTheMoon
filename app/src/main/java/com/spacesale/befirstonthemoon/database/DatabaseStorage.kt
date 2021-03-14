package com.spacesale.befirstonthemoon.database

import android.content.Context
import com.opencsv.CSVReader
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.database.entity.SectorEntity
import kotlinx.coroutines.*
import java.io.InputStreamReader

class DatabaseStorage(private val context: Context, private val db: AppDatabase) {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    private val planets: List<PlanetEntity> = listOf(
        PlanetEntity(
            planetId = 1,
            name = "Луна",
            description = "Луна́ — единственный естественный спутник земли. Самый близкий к Солнцу спутник планеты, так как у ближайших к Солнцу планет (Меркурия и Венеры) их нет. Второй по яркости объект на земном небосводе после Солнца и пятый по величине естественный спутник планеты Солнечной системы. Луна является единственным внеземным астрономическим объектом, на котором побывал человек.",
            atmosphere = "крайне разрежена, имеются следы водорода, гелия, неона и аргона",
            characteristic = "Масса - 7,3477Е22 кг\n" +
                    "Расстояние до Земли - 384 400 км\n" +
                    "Радиус - 1 737,1 км\n" +
                    "Температура - мин. -173С, макс. 117С",
            mainPoster = R.drawable.moon_select,
            detailPoster = R.drawable.moon_details_pic,
            texture = R.drawable.moon
        ),
        PlanetEntity(
            planetId = 2,
            name = "Марс",
            description = "Марс — четвёртая по удалённости от Солнца и седьмая по размеру планета Солнечной системы; масса планеты составляет 10,7 % массы Земли. Названа в честь Марса — древнеримского бога войны, соответствующего древнегреческому Аресу. Иногда Марс называют «красной планетой» из-за красноватого оттенка поверхности, придаваемого ей минералом маггемидом— γ-оксидом железа.\n",
            atmosphere = "95,32 % углекислый газ\n" +
                    "2,7 % азот\n" +
                    "1,6 % аргон\n" +
                    "0,145 % кислород\n" +
                    "0,08 % угарный газ\n" +
                    "0,021 % водяной пар\n" +
                    "0,01 % окись азота\n" +
                    "0,00025 % неон",
            characteristic = "Масса - 6,39Е23 кг\n" +
                    "Расстояние от Земли - от 55,76 до 401 млн км\n" +
                    "Радиус - 3389,5 км\n" +
                    "Температура - от −153 °C до +35 °C",
            mainPoster = R.drawable.mars_select,
            detailPoster = R.drawable.mars_details_pic,
            texture = R.drawable.mars
        ),
        PlanetEntity(
            planetId = 3,
            name = "Меркурий",
            description = "Мерку́рий — ближайшая к Солнцу планета Солнечной системы, наименьшая из планет земной группы. Названа в честь древнеримского бога торговли — быстрого Меркурия, поскольку она движется по небу быстрее других планет. Её период обращения вокруг Солнца составляет всего 87,97 земных суток — самый короткий среди всех планет Солнечной системы.",
            atmosphere = "42,0 % кислород\n" +
                    "29,0 % натрий\n" +
                    "22,0 % водород\n" +
                    "6,0 % гелий\n" +
                    "0,5 % калий\n" +
                    "0,5 % остальные (вода, углекислый газ, азот, аргон, ксенон, криптон, неон, кальций, магний)",
            characteristic = "Масса - 3,285E23 кг\n" +
                    "Расстояние от Земли - от 82 до 217 млн км\n" +
                    "Радиус - 2 439,7 км\n" +
                    "Температура - от −190 до +430 °C\n",
            mainPoster = R.drawable.mercury_select,
            detailPoster = R.drawable.mercury_details_pic,
            texture = R.drawable.mercury
        ),
    )

    private val sectors: MutableList<SectorEntity> = mutableListOf()

    fun createMockData() {
        scope.launch {
            if (db.planetDao().count() > 0)
                return@launch

            val writer = CSVReader(InputStreamReader(context.assets.open("world_boundaries.csv")))
            val csvLines: List<Array<String>>
            csvLines = writer.readAll()
            writer.close()

            var i = 0
            //moon
            csvLines.forEach {
                if (i != 0) {
                    sectors.add(
                        SectorEntity(
                            planetId = 1,
                            isSale = false,
                            price = 500000f,
                            wkt = it[0],
                            layer = it[1],
                            countryNa = it[2],
                            id = it[3].toInt(),
                            facId = it[4].toInt()
                        )
                    )
                }
                i++
            }

            //mars
            i = 0
            csvLines.forEach {
                if (i != 0) {
                    sectors.add(
                        SectorEntity(
                            planetId = 2,
                            isSale = false,
                            price = 500000f,
                            wkt = it[0],
                            layer = it[1],
                            countryNa = it[2],
                            id = it[3].toInt(),
                            facId = it[4].toInt()
                        )
                    )
                }
                i++
            }

            //mercury
            i = 0
            csvLines.forEach {
                if (i != 0) {
                    sectors.add(
                        SectorEntity(
                            planetId = 3,
                            isSale = false,
                            price = 500000f,
                            wkt = it[0],
                            layer = it[1],
                            countryNa = it[2],
                            id = it[3].toInt(),
                            facId = it[4].toInt()
                        )
                    )
                }
                i++
            }

            db.planetDao().insertAll(planets)
            db.sectorDao().insertAll(sectors)
        }

    }

}