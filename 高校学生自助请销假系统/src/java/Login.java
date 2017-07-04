/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.DerbyDemo;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String stype=request.getParameter("kind");
          
            PrintWriter pw=response.getWriter();
             DerbyDemo dm=new DerbyDemo();
             dm.createConnection();
             switch (stype){
                case "stu":
                String n=dm.selectSTUName(username);
                if(n.equals(password))
                {
                    //跳转到学生界面
                   response.sendRedirect("stu?username="+username);
                }
                else if(n.equals("false")){
                    pw.write("不存在此用户账号");
                }
                else{
                    pw.write("密码错误!");
                }
                break;
                case "adm":
                n=dm.selectADMName(username);
                if(n.equals(password))
                {
                    //跳转到管理员界面
                    response.sendRedirect("adm?username="+username);
                }
                else if(n.equals("false")){
                    pw.write("不存在此用户账号");
                }
                else{
                    pw.write("密码错误!");
                }
                break; 
                case "ass":
                n=dm.selectASSName(username);
                if(n.equals(password))
                {
                    //跳转到辅导员界面
                     response.sendRedirect("ass?username="+username);
                }
                else if(n.equals("false")){
                    pw.write("不存在此用户账号");
                }
                else{
                    pw.write("密码错误!");
                }
                break;
                case "tea":
                n=dm.selectTEAName(username);
                if(n.equals(password))
                {
                    //跳转到老师界面
                     response.sendRedirect("tea?username="+username);
                }
                else if(n.equals("false")){
                    pw.write("不存在此用户账号");
                }
                else{
                    pw.write("密码错误!");
                }
                break;
             }
             pw.close();
            dm.shutdown();
      }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void swich(String stype) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
