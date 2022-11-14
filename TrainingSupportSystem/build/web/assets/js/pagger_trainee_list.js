/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function render(id, pageindex, totalpage, gap, class_name, status, name,suportrain)
{
    var container = document.getElementById(id);
    var content = "";
    if (pageindex > gap + 1)
        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"traineelist?action=view&role"+suportrain+"&class_get=" + class_name + "&status=" + status + "&trainee_name=" + name + "&page=1\" aria-label=\"Previous\">First</a></li>";

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            content += "<li class=\"page-item\"><a class=\"page-link\" href=\"traineelist?action=view&role"+suportrain+"&class_get=" + class_name + "&status=" + status + "&trainee_name=" + name + "&page=" + i + "\">" + i + "</a></li>";

    content += "<li class=\"page-item active\"><a class=\"page-link\" href=\"traineelist?action=view&role"+suportrain+"&class_get=" + class_name + "&status=" + status + "&trainee_name=" + name + "&page=" + pageindex + "\">" + pageindex + "</a></li>";

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            content += "<li class=\"page-item\"><a class=\"page-link\" href=\"traineelist?action=view&role"+suportrain+"&class_get=" + class_name + "&status=" + status + "&trainee_name=" + name + "&page=" + i + "\">" + i + "</a></li>";

    if (pageindex < totalpage - gap)
        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"traineelist?action=view&role"+suportrain+"&class_get=" + class_name + "&status=" + status + "&trainee_name=" + name + "&page=" + totalpage + "\" aria-label=\"Previous\">Last</a></li>";
    container.innerHTML = content;
}

