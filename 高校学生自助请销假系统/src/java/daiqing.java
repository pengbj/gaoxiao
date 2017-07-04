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
@WebServlet(urlPatterns = {"/daiqing"})
public class daiqing extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String username=request.getParameter("username");
        String course=request.getParameter("course");
        String state="待审核";
        DerbyDemo dm=new DerbyDemo();
        dm.createConnection();PrintWriter pw=response.getWriter();
        if(dm.UpCourseState(username, course, state)){
            pw.println("申请审核成功！");
        }
        else{
            pw.println("未知原因！申请失败");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
