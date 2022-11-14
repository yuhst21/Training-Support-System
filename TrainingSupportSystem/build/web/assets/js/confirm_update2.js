/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function modal_open(id) {

    var html = document.getElementById("model_content");
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id\" value=" + id + ">";
    window.location.href = "#demo-modal";



}


function modal_open2() {
    var from_time = document.getElementById('from_time').value;
    var to_time = document.getElementById('to_time').value;
    var min_chars = 0;
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;

    var today = year + "-" + month + "-" + day;
    var training_date = document.getElementById('date').value;
    if (from_time === "") {
        var content = document.getElementById('txt1');
        content.innerHTML = "<p id=\"buoi\" style=\"color: red\">From time can't be empty</p>";
    } else {
        var content1 = document.getElementById('txt1');
        content1.innerHTML = "";


    }

    if (to_time === "") {
        var content = document.getElementById('check');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">To time can't be empty</p>";
    } else if (from_time > to_time && to_time !== null && from_time !== null) {
        var content = document.getElementById('check');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">To time can't be samller than To time</p>";

    } else {
        var content1 = document.getElementById('check');
        content1.innerHTML = "";

    }


    if (!training_date) {
        var content = document.getElementById('checkdate');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">Date can't be empty</p>";
    } else {
       var content1 = document.getElementById('checkdate');
        content1.innerHTML = "";
    }
    
     if (from_time < to_time && to_time !== null && from_time !== null && training_date ) {
        var html = document.getElementById("model_content");
       
        window.location.href = "#demo-modal";
    }
}

function modal_close() {

    window.location.href = "#";
}


function modal_change() {
        window.location.href = "#demo-modal2";


}

function modal_close2() {
   document.getElementById("dcmm").submit();
}