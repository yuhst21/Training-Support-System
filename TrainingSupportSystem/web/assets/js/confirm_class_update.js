/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function limit(element)
{
    var max_chars = 200;
    var min_chars = 0;
    var content = document.getElementById('textvalidate');

    if (element.value.length > max_chars) {
        var content = document.getElementById('textvalidate');
        content.innerHTML = "<p id=\"noti\" style=\"color: red\">Text can't exceed 200 characters</p>";
    }
    if (element.value.length < max_chars && element.value.length > min_chars) {
        var content = document.getElementById('noti');
        content.innerHTML = "";
    }
    if (element.value.length === min_chars) {
        var content = document.getElementById('textvalidate');
        content.innerHTML = "<p id=\"noti\" style=\"color: red\">Text can't be empty</p>";
    }
}
function modal_open(id) {
    var html = document.getElementById("model_content");
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id\" value=" + id + ">";
    window.location.href = "#demo-modal";
}


function modal_open2() {
    var content = document.getElementById('textid');
    var max_chars = 201;
    var min_chars = 0;
    if (content.value.length < max_chars && content.value.length > min_chars) {
        var html = document.getElementById("model_content");
        window.location.href = "#demo-modal";
    }
}

function modal_close() {

    window.location.href = "#";
}


function modal_change() {
      document.getElementById("frm").submit();
}

function modal_close2() {
   window.location.href = "#";
}


