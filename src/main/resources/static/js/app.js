var author = (function () {

  return{
    getBlueprints : function () {
        let author = document.getElementById('name').value;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            let table = document.getElementById('table');
            var responseText = this.responseText;
            console.log('res')
            console.log(responseText);
            let json = JSON.parse(responseText);
            console.log(json);
            table.style.display = '';
            for (key in json) {
                console.log(responseText);
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

var drawBlueprint = function (index, responseText) {
    //let json = JSON.parse(responseText);
    console.log(responseText);
    console.log(index);
    let canvas = document.getElementById('canva');
    canvas.style['display'] = 'flex';

  };