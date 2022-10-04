let openedCanva;

let context;

var canvas = (function () {

  return{
    deleteBlueprint : function () {
      const xhttp = new XMLHttpRequest();
      xhttp.onload = function () {
        console.log("borrando")
        let table = document.getElementById('table');
        var responseText = this.responseText;
        console.log(responseText);
        let json = JSON.parse(responseText);
        context = json;
        cleanData([...document.getElementsByClassName("row")]);
        table.style.display = '';
        for (key in json) {
            let row = `<tr class='row'>
                          <td>${json[key]['name']}</td>
                          <td>${json[key]['points'].length}</td>
                          <td><input type='button' value='Open' onclick='drawBlueprint( ${key}, ${responseText})'></td>
                        </tr>`
            table.innerHTML += row;
        }
    }
    xhttp.open('DELETE', '/blueprints/'+ context[openedCanva]['author']+"/"+context[openedCanva]["name"])
    xhttp.send();
    }
  };

})();

var author = (function () {

  return{
    getBlueprints : function () {
        let author = document.getElementById('name').value;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            let table = document.getElementById('table');
            var responseText = this.responseText;
            let json = JSON.parse(responseText);
            context= json;
            table.style.display = '';
            for (key in json) {
                let row = `<tr class='row'>
                              <td>${json[key]['name']}</td>
                              <td>${json[key]['points'].length}</td>
                              <td><input type='button' value='Open' onclick='drawBlueprint( ${key}, ${responseText})'></td>
                            </tr>`
                table.innerHTML += row;
            }
        }
        xhttp.open('GET', '/blueprints/'+ author)
        xhttp.send();
    }
  };

})();


var cleanData = function(data) {
  data.forEach(element => {
      element.remove();
  });
}

var drawBlueprint = function (index, responseText) {
    let canvas = document.getElementById('canva');
    var ctx = canvas.getContext("2d");
    openedCanva = index;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    canvas.style['display'] = 'flex';
    let points = responseText[index]['points'];
    ctx.beginPath();
    for (let i = 0; i < points.length-1; i++) {
      ctx.moveTo(points[i]['x'], points[i]['y']);
      ctx.lineTo(points[i+1]['x'], points[i+1]['y']);
    }
    ctx.stroke();
  };