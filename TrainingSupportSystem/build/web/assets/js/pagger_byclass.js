/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function render(id, pageindex, totalpage, gap, sup_id, train_id, class_id, status, manager)
{
    var container = document.getElementById(id);
    var content = "";
    if (pageindex > gap + 1)
        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"classlist?action=view&mana=" + manager + "&class_get=" + class_id + "&support_get=" + sup_id + "&trainer_get=" + train_id + "&status=" + status + "&page=1\" aria-label=\"Previous\">First</a></li>";

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            content += "<li class=\"page-item\"><a class=\"page-link\" href=\"classlist?action=view&mana=" + manager + "&class_get=" + class_id + "&support_get=" + sup_id + "&trainer_get=" + train_id + "&status=" + status + "&page=" + i + "\">" + i + "</a></li>";

    content += "<li class=\"page-item active\"><a class=\"page-link\" href=\"classlist?action=view&mana=" + manager + "&class_get=" + class_id + "&support_get=" + sup_id + "&trainer_get=" + train_id + "&status=" + status + "&page=" + pageindex + "\">" + pageindex + "</a></li>";

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            content += "<li class=\"page-item\"><a class=\"page-link\" href=\"classlist?action=view&mana=" + manager + "&class_get=" + class_id + "&support_get=" + sup_id + "&trainer_get=" + train_id + "&status=" + status + "&page=" + i + "\">" + i + "</a></li>";

    if (pageindex < totalpage - gap)
        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"classlist?action=view&mana=" + manager + "&class_get=" + class_id + "&support_get=" + sup_id + "&trainer_get=" + train_id + "&status=" + status + "&page=" + totalpage + "\" aria-label=\"Previous\">Last</a></li>";
    container.innerHTML = content;
}



