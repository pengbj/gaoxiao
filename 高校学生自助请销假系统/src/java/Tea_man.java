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
@WebServlet(urlPatterns = {"/NewServlet2"})
public class Tea_man extends HttpServlet {

  

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        response.setContentType("text/html;charset=gbk");
        PrintWriter pw=response.getWriter();
        DerbyDemo dm=new DerbyDemo();
        dm.createConnection();
        List<String>list=new ArrayList<String>();
        list=dm.selectHAudStudent(username);
       
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <meta charset=\"gbk\">");
            out.println("<title>老师界面</title>");    
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action=\"daiqing\" method=\"POST\" name=\"daiqing\" >");
            out.println(" 请假学生与课程信息： <br>"); 
            for(int i=0;i<list.size();i++){
               String s=list.get(i);
               String [] ss=s.split(" ");
                out.println("<input type=\"text\" name=\"qingjia\" value=\"课程："+ss[0]+" 学生："+ss[1]+ " 请假\" size=\"30\" readonly=\"readonly\" /><br>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      doGet(request,response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
