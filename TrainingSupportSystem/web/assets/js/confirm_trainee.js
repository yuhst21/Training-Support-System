/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function change_confirm(id, class_id) {
    document.getElementById("status_form_" + id + "_" + class_id).submit();
}

function modal_open(id, class_id, status) {
    var html = document.getElementById("model_content");
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id\" value=" + id + ">";
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id1\" value=" + class_id + ">";
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id2\" value=" + status + ">";
    if (status) {
        var content = document.getElementById('test');
        content.innerHTML += "<input type=\"date\" name=\"date\" >";
        content.innerHTML += "<input type=\"hidden\" name=\"id\" value=" + id + ">";
        content.innerHTML += "<input type=\"hidden\" name=\"class_id\" value=" + class_id + ">";
    }
    window.location.href = "#demo-modal";
}

function modal_close() {
    var id = document.getElementById("modal_id").value;
    var status = document.getElementById("modal_id2").value;
    var status = document.getElementById("status_check_" + id);
    const element = document.getElementById("modal_id");
    element.remove();
    var content = document.getElementById('get_date');
    content.innerHTML = "";
    status.checked = !status.checked;
    window.location.href = "#";
}

function modal_change() {
    var id = document.getElementById("modal_id").value;
    var class_id = document.getElementById("modal_id1").value;
    //document.getElementById("test").submit();
    change_confirm(id, class_id);
}

function modal_close2() {

    window.location.href = "#";
}
