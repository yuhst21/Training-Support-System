function render(id, pageindex, totalpage, gap, txt, role, status)
            {
                var container = document.getElementById(id);
                var content = "";
                if (pageindex > gap + 1)
                    content += "<li class=\"page-item\"><a class=\"page-link\" href=\"users?action=view&txtsearch=" + txt + "&filter_status=" + status + "&filter_role=" + role + "&page=1\" aria-label=\"Previous\">First</a></li>";

                for (var i = pageindex - gap; i < pageindex; i++)
                    if (i > 0)
                        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"users?action=view&txtsearch=" + txt + "&filter_status=" + status + "&filter_role=" + role  + "&page=" + i + "\">" + i + "</a></li>";

                content += "<li class=\"page-item active\"><a class=\"page-link\" href=\"users?action=view&txtsearch=" + txt + "&filter_status=" + status + "&filter_role=" + role + "&page=" + pageindex + "\">" + pageindex + "</a></li>";

                for (var i = pageindex + 1; i <= pageindex + gap; i++)
                    if (i <= totalpage)
                        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"users?action=view&txtsearch=" + txt + "&filter_status=" + status + "&filter_role=" + role + "&page=" + i + "\">" + i + "</a></li>";

                if (pageindex < totalpage - gap)
                    content += "<li class=\"page-item\"><a class=\"page-link\" href=\"users?action=view&txtsearch=" + txt + "&filter_status=" + status + "&filter_role=" + role + "&page=" + totalpage + "\" aria-label=\"Previous\">Last</a></li>";
                container.innerHTML = content;
            }