/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.DerbyDemo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author w
 */
public class Stu_man extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String username=request.getParameter("username");
        PrintWriter pw=response.getWriter();
        DerbyDemo dm=new DerbyDemo();
        dm.createConnection();
        List<String>list=new ArrayList<String>();
         List<String>list1=new ArrayList<String>();
       list=dm.selectLeaveCourse(username);
       list1=dm.selectALeaveCourse(username);
       if(list==null&&list1==null){
           pw.println("无相关操作");
       }
       else{
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <meta charset=\"gbk\">");
            out.println("<title>学生界面</title>");    
            out.println("</head>");
            out.println("<body>");
            if(list!=null){
            out.println(" <form action=\"daiqing\" method=\"POST\" name=\"daiqing\"  >");
            out.println(" 可请假课程： <select name=\"course\">"); 
            for(int i=0;i<list.size();i++){
               
                out.println("<option>"+list.get(i)+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"username\" value="+username+"> ");
            out.println("<input type=\"submit\" value=\"leave\" />");
            out.println("</form>");
            }
            if(list1!=null){
            out.println(" <form action=\"daixiaojia\" method=\"POST\" name=\"daixiaojia\" >");
            out.println(" 可销假课程： <select name=\"course\">"); 
            for(int i=0;i<list1.size();i++){
               
                out.println("<option>"+list1.get(i)+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"username\" value="+username+"> ");
            out.println("<input type=\"submit\" value=\"absence\" />");
            out.println("</form>");
            }
            out.println("</body>");
            out.println("</html>");
        }
       }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
