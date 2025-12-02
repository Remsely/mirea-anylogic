async function sendJsonRequest() {
    const scenarioNumber = document.getElementById('scenarioNumber').value;
    const drillingRate = document.getElementById('drillingRate').value;
    const oilPrice = document.getElementById('oilPrice').value;
    const exchangeRate = document.getElementById('exchangeRate').value;

    const jsonData = {
        scenarioNumber: Number(scenarioNumber),
        drillingRate: Number(drillingRate),
        oilPrice: Number(oilPrice),
        exchangeRate: Number(exchangeRate)
    };

    const responseEl = document.getElementById('response');
    responseEl.style.visibility = 'hidden';

    try {
        const response = await axios.post("/api/model", jsonData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.status === 200) {
            responseEl.innerHTML = '<strong>Успех:</strong> Запись успешно добавлена!';
            responseEl.className = 'response success';
        } else {
            responseEl.innerHTML = `<strong>Ответ:</strong> ${JSON.stringify(response.data, null, 2)}`;
            responseEl.className = 'response';
        }
    } catch (error) {
        responseEl.innerHTML = `<strong>Ошибка:</strong> ${error.response?.status || ""} ${error.message}`;
        responseEl.className = 'response error';
    }

    responseEl.style.visibility = 'visible';
}