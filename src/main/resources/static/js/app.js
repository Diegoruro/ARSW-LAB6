var author = (function () {

  return{
    getBlueprints : function () {
        let author = document.getElementById("name").value;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            let table = document.getElementById("table");
            let json = JSON.parse(this.responseText);
            table.style.display = "";
            let mainData = json[Object.keys(json)[1]];
            let num = 0;
            for (key in mainData) {
                let row = `<tr class="row">
                                                                                  <td>${key}</td>
                                                                                  <td>${mainData[key]["1. open"]}</td>
                                                                                  <td>${mainData[key]["2. high"]}</td>
                                                                                  <td>${mainData[key]["3. low"]}</td>
                                                                                  <td>${mainData[key]["4. close"]}</td>
                                                                                  <td>${mainData[key]["5. volume"]}</td>
                                                                        </tr>`
                table.innerHTML += row;
                num++;
            }
        }
        xhttp.open("GET", "/blueprints/"+ author)
        xhttp.send();
    }
  };

  return{
      buildTable : function (json){
              console.log(json)
          }
  };
})();