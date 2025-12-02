package edu.mirea.remsely.anylogic.practice11.oilcompanyrevenue.controller

import com.anylogic.engine.Engine
import edu.mirea.remsely.anylogic.practice11.oilcompanyrevenue.dto.CreateRequest
import model.CustomExperiment
import model.Main
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin("*")
@RequestMapping("api/model")
class MainController {
    @PostMapping
    fun runModel(@RequestBody request: CreateRequest): Map<String, String> {
        val engine: Engine = CustomExperiment(null).createEngine();

        val main = Main(engine, null, null).also {
            it.setParametersToDefaultValues()
        }

        engine.start(main);

        main.apply {
            Сценарий = request.scenarioNumber
            Темп_бурения = request.drillingRate
            Цена_на_нефть = request.oilPrice
            Курс_доллара = request.exchangeRate
        }

        engine.apply {
            startDate = Date()
            stopDate = Calendar.getInstance().apply { add(Calendar.YEAR, 30) }.time;
            realTimeMode = false;

            while (time < 30) {
                step()
            }
            stop()
        }

        return mapOf(
            "message" to "Model run completed.",
        )
    }
}
