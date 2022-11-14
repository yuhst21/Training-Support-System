/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function change_confirm(id) {
    document.getElementById("status_form_" + id).submit();
}

function modal_open(id) {
    var html = document.getElementById("model_content");
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id\" value=" + id + ">";
    window.location.href = "#demo-modal";
}

function modal_close() {
    var id = document.getElementById("modal_id").value;
    var status = document.getElementById("status_check_" + id);
    const element = document.getElementById("modal_id");
    element.remove();
    status.checked = !status.checked;
    window.location.href = "#";
}


function modal_change() {
    var id = document.getElementById("modal_id").value;
    change_confirm(id);
}