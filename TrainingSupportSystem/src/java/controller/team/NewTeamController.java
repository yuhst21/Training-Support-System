/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.team;

import static controller.TraineeListController.COLUMN_INDEX_ID_USER;
import static controller.TraineeListController.COLUMN_INDEX_PRICE;
import static controller.TraineeListController.COLUMN_INDEX_TOTAL;
import dal.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import model.Class_user;
import model.Team;
import model.Team_member;
import model.User;
import model.Class;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static controller.TraineeListController.COLUMN_INDEX_NAME;
import dal.ClassUserDAO;
import jakarta.servlet.annotation.MultipartConfig;
import static controller.TraineeListController.COLUMN_INDEX_EMAIL;
import model.Milestone;


/**
 *
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class NewTeamController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListTeamController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListTeamController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    Random rd = new Random();
    TeamDAO t = new TeamDAO();
//    List<Class_user> list = t.list(1);
    List<User> list1 = new ArrayList<>();
    List<Team> list2 = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        HttpSession session = request.getSession();
        String class_id = request.getParameter("class");
        String milestone = request.getParameter("milestone");
        List<Milestone> listMileStone = t.getAllMileStone();
        int class_id2 = Integer.parseInt(class_id);
        List<Class_user> list = t.list(class_id2);
       List<Class> listClass = t.getAllClass(1);
         session.setAttribute("data", list);
        request.setAttribute("data", list);
        request.setAttribute("class_id", class_id);
        request.setAttribute("milestone", milestone);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listMilestone", listMileStone);
        request.getRequestDispatcher("view/team/team_create.jsp").forward(request, response);
//        request.getRequestDispatcher("view/team/list_team.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ClassUserDAO cdao = new ClassUserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String milestone = request.getParameter("milestone");
        String s = "";
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            // part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
            s = fileName;
        }
        String class_id = request.getParameter("class");
        String excelFilePath = "D:\\"+s;
        List<Class_user> class_user = readExcel(excelFilePath);
        HttpSession session = request.getSession();
        
        session.setAttribute("list_team_upload", class_user);
        request.setAttribute("milestone", milestone);
        request.setAttribute("class_id", class_id);
        request.setAttribute("class_user", class_user);
        
        request.getRequestDispatcher("view/team/upload_team.jsp").forward(request, response);
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
                        u.setUser_id(new BigDecimal((double) cellValue).intValue());

                        break;
                    case COLUMN_INDEX_NAME:
                        u.setFull_name((String) getCellValue(cell));
                        //book.setTitle((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_EMAIL:
                        //book.setQuantity(new BigDecimal((double) cellValue).intValue());
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
            cl.setUser(u);
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
