/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.DerbyDemo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author w
 */
@WebServlet(urlPatterns = {"/shenghe"})
public class shenghe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String s=request.getParameter("mess");
        String[] sm=s.split(" ");
        String username=sm[0].substring(sm[0].indexOf(":")+1, sm[0].length());
        String course=sm[1].substring(sm[1].indexOf(":")+1, sm[1].length());
        String state="已审核";
        DerbyDemo dm=new DerbyDemo();
        dm.createConnection();PrintWriter pw=response.getWriter();
        if(dm.UpCourseState(username, course, state)){
            pw.println("审核成功！");
        }
        else{
            pw.println("未知原因！审核失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
