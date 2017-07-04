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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author w
 */
public class Ass_man extends HttpServlet {

   
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
        list=dm.selectAudStudent(username);
        list1=dm.selectAAudStudent(username);
        if(list==null&&list1==null){
            pw.println("没有需要进行的工作！");
        }
        else{
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <meta charset=\"gbk\">");
            out.println("<title>辅导员界面</title>");    
            out.println("</head>");
            out.println("<body>");
            if(list!=null){
            out.println(" <form action=\"shenghe\"  name=\"shenghe\" >");
            out.println(" 待审核请假： <select name=\"mess\">"); 
            for(int i=0;i<list.size();i++){
               String s=list.get(i);
               String [] ss=s.split(" ");
               String sm="student:"+ss[0]+" course:"+ss[1];
               
                out.println("<option>"+sm+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"ensure\" />");
            out.println("</form>");
            }
            if(list1!=null){
                out.println(" <form action=\"shengxiao\"  name=\"shengxiao\" >");
            out.println(" 待审核销假： <select name=\"mess\">"); 
            for(int i=0;i<list1.size();i++){
               String s=list1.get(i);
               String [] ss=s.split(" ");
               String sm="student:"+ss[0]+" course:"+ss[1];
               
                out.println("<option>"+sm+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"ensure\" />");
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
        doGet(request, response);
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
