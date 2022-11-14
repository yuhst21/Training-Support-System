/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import common.SendEmail;
import dal.ClassSettingDAO;
import dal.ClassUserDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Class_user;
import model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author win
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class TraineeListController extends HttpServlet {

    UserDAO uDB = new UserDAO();
    ClassSettingDAO cDB = new ClassSettingDAO();
    ClassUserDAO clDB = new ClassUserDAO();
    public static final int COLUMN_INDEX_ID_USER = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_EMAIL = 2;
    public static final int COLUMN_INDEX_PRICE = 3;
    public static final int COLUMN_INDEX_TOTAL = 4;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<div id=\"droppedout_check\" style=\"visibility:hidden\">\n"
                + "Dropped Out Date: <input type=\"date\" name=\"droppedout_date\"  value=\"" + out + "\">   ");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = uDB.get(u);
        
        ////--User Login END--/////
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        request.getSession().setAttribute("userMan", u.getUser_id());
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                String class_user = request.getParameter("user_id");
                String class_id = request.getParameter("class_id");
                User us = new User();
                us.setUser_id(Integer.parseInt(class_user));
                us = uDB.get(us);
                model.Class c = new model.Class();
                c.setClass_id(Integer.parseInt(class_id));
                Class_user cu = new Class_user();
                cu.setUser(us);
                cu.setClass_id(c);
                cu = clDB.get(cu);
                request.setAttribute("user_class", cu);
                request.setAttribute("mes_update", "");
                request.getRequestDispatcher("view/trainee/traineedetail.jsp").forward(request, response);
                break;
            case "view":
                request.setAttribute("list_class", cDB.listClassByUser(u.getUser_id()));
                listTrainee(request, response);
                break;
            case "import":
                request.getRequestDispatcher("view/trainee/traineeimport.jsp").forward(request, response);
                break;

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                editTrainee(request, response);
                break;
            case "import":
                importTrainee(request, response);
                break;

        }
    }

    protected void listTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = uDB.get(u);    
        String raw_class = request.getParameter("class_get");
        String raw_name = request.getParameter("trainee_name");
        String raw_status = request.getParameter("status");
        String class_user = (raw_name != null && raw_name.length() > 0) ? raw_name : "";
        Integer class_id = (raw_class != null && !raw_class.equals("-1")) ? new Integer(raw_class) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");
        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 5;
        int count = clDB.count(class_id, status, class_user,u.getUser_id());
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String id_class = arr[1];
            boolean raw_statuss = Boolean.parseBoolean(arr[2]);
            Date date = null;
            if (Boolean.parseBoolean(raw_status)) {
                date = null;
            } else {
                LocalDate now = LocalDate.now();
                date = Date.valueOf(now);
            }
            clDB.updateStatus(Integer.parseInt(id), Integer.parseInt(id_class), raw_statuss, date);
        }

        ArrayList<Class_user> class_user_list = clDB.paggingV2(page_size, page_index, class_id, status, class_user,u.getUser_id());
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("total_page", total_page);
        request.setAttribute("class_id", class_id);
        request.setAttribute("class_user", class_user);
        request.setAttribute("status", status);
        request.setAttribute("count", count);
        request.setAttribute("class_user_list", class_user_list);
        request.getRequestDispatcher("view/trainee/traineelist.jsp").forward(request, response);
    }

    protected void editTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get Raw value
        String raw_dropped_out_date = request.getParameter("droppedout_date");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("note");
        //validate raw value
        int user_id = Integer.parseInt(request.getParameter("users_id"));
        int class_id = Integer.parseInt(request.getParameter("classes_id"));
        Date dropped_out = (raw_dropped_out_date != null && raw_dropped_out_date.length() > 0)
                ? Date.valueOf(raw_dropped_out_date) : null;
        String status = (raw_status != null && !raw_status.equals("-1")) ? raw_status : "-1";
        String note = (raw_des != null && raw_des.length() > 0) ? raw_des : null;
        User u = new User();
        u.setUser_id(user_id);
        model.Class c = new model.Class();
        c.setClass_id(class_id);
        if (status.equals("true")) {
            dropped_out = null;
        }
        //create new user class to update value
        Class_user cu = new Class_user(c, u, Boolean.parseBoolean(status), note, dropped_out);
        clDB.update(cu);
        cu = clDB.get(cu);
        request.setAttribute("user_class", cu);
        request.setAttribute("mes_update", "Update sucessfull");
        response.sendRedirect("traineelist?action=edit&user_id="+u.getUser_id()+"&class_id="+c.getClass_id()+"#demo-modal2");
        //request.getRequestDispatcher("view/trainee/traineedetail.jsp").forward(request, response);

    }

    protected void importTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get path of file import 
        String s = "";
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            // part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
            s = fileName;
        }
        String excelFilePath = "C:\\Users\\win\\Desktop\\" + s;
        //read data from excel
        List<Class_user> class_user = readExcel(excelFilePath);
        int class_id = Integer.parseInt(request.getParameter("class_choice"));
        SendEmail send = new SendEmail();
        model.Class c = new model.Class();
        c.setClass_id(class_id);
        Class_user cu = new Class_user();
        cu.setClass_id(c);
        // clDB.delete(cu);
        //set class for user read from excel
        for (Class_user clas : class_user) {
            clas.setClass_id(c);
        }
//        for (Class_user cl : class_user) {
//            clDB.insert(cl);
//        }
        //check user in excel is existed in class or not
        ArrayList<Class_user> tamp1 = new ArrayList<>();
        for (Class_user clu : class_user) {
            if (clDB.checkUserExist(clu)) {
                clDB.insert(clu);
            } else {
                tamp1.add(clu);
            }
        }

        //re call list when insert new infor to class
        ArrayList<Class_user> temp = clDB.list(cu.getClass_id().getClass_id());
        //check role to send email
        for (Class_user cuer : temp) {
            if (cuer.getUser().getList().get(0).getSetting_id() == 16) {
                String code = send.sendEmailToGuest(cuer.getUser().getEmail());
                clDB.updateToTrainee(cuer);
                clDB.updatePassTrainee(cuer, code);
            }
        }
        request.setAttribute("class_user", class_user);
        response.sendRedirect("traineelist?action=view&class_get=" + cu.getClass_id().getClass_id() + "#demo-modal2");
    }

    private static final long serialVersionUID = 1L;

    /**
     * Extracts file name from HTTP header content-disposition
     *///
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    public static List<Class_user> readExcel(String excelFilePath) throws IOException {
        List<Class_user> classList = new ArrayList<>();
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            // Read cells and set value for book object
            Class_user cl = new Class_user();
            User u = new User();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_ID_USER:
                        // book.setId(new BigDecimal((double) cellValue).intValue());
//                        u.setUser_id(new BigDecimal((double) cellValue).intValue());
//                        cl.setUser(u);
                        break;
                    case COLUMN_INDEX_NAME:
                        //book.setTitle((String) getCellValue(cell));
                        u.setFull_name((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_EMAIL:
                        //book.setQuantity(new BigDecimal((double) cellValue).intValue());
                        u.setEmail((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PRICE:
                        //book.setPrice((Double) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_TOTAL:
                        // book.setTotalMoney((Double) getCellValue(cell));
                        break;
                    default:
                        break;
                }
            }
            UserDAO udb = new UserDAO();
            ArrayList<User> user = udb.list();
            int countCheck = 0;
            for (User use : user) {
                if (!use.getEmail().equals(u.getEmail())) {
                    countCheck = 1;
                } else {
                    countCheck = 0;
                    break;
                }
            }
            if (countCheck == 1) {
                int count = udb.countID();
                User usernew = new User(count + 1, u.getFull_name(), u.getEmail(), true);
                udb.insert(usernew);
                udb.insertRoleForNewUser(usernew);
                cl.setUser(usernew);
            } else if (countCheck == 0) {
                int userbyEmail = udb.getIdByEmail(u.getEmail());
                User userolde = new User();
                userolde.setUser_id(userbyEmail);
                cl.setUser(userolde);
            }
            classList.add(cl);
        }
        workbook.close();
        inputStream.close();
        return classList;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
