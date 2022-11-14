/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function statusCheck() {
    if (document.getElementById('droppedout').checked) {
        document.getElementById('droppedout_check').style.visibility = 'visible';

    } else {
        document.getElementById('droppedout_check').style.visibility = 'hidden';
    }
}
function limit(element)
{
    var max_chars = 200;
    var min_chars = 0;
    var content = document.getElementById('textvalidate');

    if (element.value.length > max_chars) {
        var content = document.getElementById('textvalidate');
        content.innerHTML = "<p id=\"noti\" style=\"color: red\">Text can't exceed 200 characters</p>";
    }
    if (element.value.length < max_chars) {
        var content = document.getElementById('noti');
        content.innerHTML = "";
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
    var training_date = document.getElementById('date').value;
//
//    if (!training_date) {
//        var content1 = document.getElementById('checkdate');
//        content1.innerHTML = "<p id=\"cac\" style=\"color: red\">Date can't be empty</p>";
//    } else {
//        var content1 = document.getElementById('checkdate');
//        content1.innerHTML = "";
//    }

    var html = document.getElementById("model_content");
    window.location.href = "#demo-modal";

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
