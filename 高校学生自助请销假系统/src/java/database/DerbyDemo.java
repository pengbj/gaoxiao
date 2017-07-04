package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DerbyDemo {
     private static String dbURL = "jdbc:derby://localhost:1527/MyDataBase;create=true;user=adm;password=123";
    private static String tableName = "STUDENT";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    public void DerbyDemo() {
    }
    public void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();            
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    public  String selectSTUName(String username) {
        try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select stu_pw from STUDENT where stu_us='"+username+"'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           String s=results.getString(1);System.out.println(s); 
           stmt.close();
           return s;
            }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return "false";
    }
     public  String selectADMName(String username) {
        try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select adm_pw from ADMINISTRATOR where adm_us='"+username+"'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           String s=results.getString(1);System.out.println(s); 
           stmt.close();
           return s;
            }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return "false";
    }
      public  String selectTEAName(String username) {
        try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select th_pw from TEACHER where th_us='"+username+"'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           String s=results.getString(1);System.out.println(s); 
           stmt.close();
           return s;
            }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return "false";
    }
      /*查询某学生可以请假的课程，用于请假*/
      public List selectLeaveCourse(String username){
          List<String> list=new ArrayList<String>();
            try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select course from stu_course where stu_us='"+username+"' and state='正常'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           list.add(results.getString(1));
           stmt.close();
           return list;
           }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
         return null;
      }
      /*查询待销假课程*/
       public List selectALeaveCourse(String username){
          List<String> list=new ArrayList<String>();
            try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select course from stu_course where stu_us='"+username+"' and state='已审核'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           list.add(results.getString(1));
           stmt.close();
           return list;
           }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
         return null;
      }
      /*
      查询所有需要审核的请假信息
      将查询的学生课程信息用 学生？课程 的形式表示。
      用于辅导员去完成审核工作
      */
       public List selectAudStudent(String username){
          List<String> list=new ArrayList<String>();
            try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select sc.stu_us,sc.course  from st_ass sa  join  stu_course sc on sa.ST_US=sc.STU_US where sa.AS_US='"+username+"' and sc.\"STATE\"='待审核'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           list.add(results.getString(1)+" "+results.getString(2));
           stmt.close();
           return list;
           }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
         return null;
      }
       /*
       查询所有需要审核的销假信息
      将查询的学生课程信息用 学生？课程 的形式表示。
      用于辅导员去完成审核工作
       */
         public List selectAAudStudent(String username){
          List<String> list=new ArrayList<String>();
            try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select sc.stu_us,sc.course  from st_ass sa  join  stu_course sc on sa.ST_US=sc.STU_US where sa.AS_US='"+username+"' and sc.\"STATE\"='待销假'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           list.add(results.getString(1)+" "+results.getString(2));
           stmt.close();
           return list;
           }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
         return null;
      }
         /*
         向表message添加某学生的请假信息。
         
         */
         public boolean addMessage(String username,String course){
             try{
              stmt=conn.createStatement();
              stmt.executeUpdate("insert into message values('"+username+"','"+course+"')");
              stmt.close();
              return true;
          }
          catch(SQLException sqlExcept){
              sqlExcept.printStackTrace();
          }
          return false;
         }
       /*
       查询所有已审核的信息
       消息以 课程？学生 的形式返回
       用于向老师提示某课程的某学生请假了
       */
         public List selectHAudStudent(String username){
          List<String> list=new ArrayList<String>();
            try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select sc.COURSE,sc.STU_US  from course c  join  stu_course sc on c.C_NAME=sc.COURSE where c.TEACHER='"+username+"' and sc.\"STATE\"='已审核'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           list.add(results.getString(1)+" "+results.getString(2));
           stmt.close();
           return list;
           }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
         return null;
      }
         
      /*
      更新某学生的课程信息状态
      */
      public Boolean UpCourseState(String username,String Course ,String state){
          try{
              stmt=conn.createStatement();
              stmt.executeUpdate("UPDATE stu_course SET state = '"+state+"' WHERE  stu_us= '"+username+"'and course='"+Course+"'");
              stmt.close();
              return true;
          }
          catch(SQLException sqlExcept){
              sqlExcept.printStackTrace();
          }
          return false;
      }
       public  String selectASSName(String username) {
        try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("select as_pw from ASSISTANT where as_us='"+username+"'");
           ResultSetMetaData rsmd = results.getMetaData();
           while (results.next()){
           String s=results.getString(1);System.out.println(s); 
           stmt.close();
           return s;
            }

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return "false";
    }
 public void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }
    }
}
