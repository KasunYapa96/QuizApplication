/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapplication;

/**
 *
 * @author Don Liyanage
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Query_Class {
    private ResultSet rs;
    private ResultSet rs1;
    private PreparedStatement pst;
    private PreparedStatement pst1;
    private Connection conn;
    private Connection con;
    String quizno;
    String question;
    String answer4;
    String answer1;
    String answer2;
    String answer3;
    String title;
    String tempAns;
    String note;
    String UserName;
    int mark;
    int Score;
     int temp;
            
    /* public void clarifyUser(String username, String password) {
      
          conn=DBConnection.getConnection();
         try{
           
           
          //  String aquery="select a_pass from admin where a_user=?";
            String uquery="select from student where username=? and password=? ";
            
            pst=conn.prepareStatement(uquery);
            pst.setString(1,username);
            pst.setString(2,password);
            rs=pst.executeQuery();
            rs.next();
            
        
             if(rs.getString(1).equals(rs.getString(2)))
            {
                UserHome y=new UserHome();
                y.setVisible(true);
            }
           
          
              
          
             
             
           
        } catch(SQLException e){
     
            System.out.println("Error:"+e);
            
        }
         
               
  } */
      void clarifyUser(String username, String password){
          
          System.out.println("Your are the " +username); 
          
         conn=DBConnection.getConnection();
         
         
             
         
         try{
             String aquery = "SELECT username, password FROM student WHERE username=? AND password=? ";
             pst=conn.prepareStatement(aquery);
             pst.setString(1, username);
            pst.setString(2, password);
            
            rs = pst.executeQuery();
            
            
            
             
             
             if(rs.next()){
             
                 if(username.startsWith("ad")){
                      AdminHome z = new AdminHome();
                     z.setVisible(true);
                      z.welcome.setText("WELCOME "+rs.getString(1)+"!");
                 }
                 else{
                     UserHome uh = new UserHome();
                     uh.setVisible(true);
                     uh.jLabel1.setText("WELCOME "+rs.getString(1)+"!");
                 }
                 
                
             }
             
         }catch(SQLException e){
             System.out.println("Error"+e);
         }
         
        
         
     }
     void authenticateUser(String fname,String mail,String uname,String pass,String cpass){
         
         conn=DBConnection.getConnection();
         try{
              
              if(cpass == null ? pass == null : cpass.equals(pass)){
              String query = "INSERT INTO student (fullname,email,username,password) VALUES(?,?,?,?)";
              pst=conn.prepareStatement(query);
              pst.setString(1, fname);
              pst.setString(2,mail);
              pst.setString(3, uname);
              pst.setString(4,pass);
              
              pst.executeUpdate();
            JOptionPane.showMessageDialog(null," Account created Sucessfully"); 
         } else{
                  JOptionPane.showMessageDialog(null,"Passwords don't match please enter again");
                 
              }
              
         }catch(SQLException ex){
             System.out.println(ex);
         }
         
     }
     
     public void addquiz(String question,String answer1,String answer2,String answer3,String answer4,String c_answer){
         conn=DBConnection.getConnection();
         System.out.println("connected");
         try{
             String query="insert into quiz (question,ans1,ans2,ans3,ans4,c_ans) values(?,?,?,?,?,?)";
             pst=conn.prepareStatement(query);
             pst.setString(1, question);
             pst.setString(2,answer1);
             pst.setString(3,answer2);
             pst.setString(4, answer3);
             pst.setString(5,answer4);
             pst.setString(6,c_answer);
             pst.executeUpdate();
             JOptionPane.showMessageDialog(null," Question added Sucessfully");
             
             
         }catch(Exception e){
             System.out.println("error:"+e);
         }
     }
     
     public void addNote(String title, String note){
         conn=DBConnection.getConnection();
         try{
             String query="INSERT INTO note (title,description) VALUES (?,?)";
            pst=conn.prepareStatement(query);
             pst.setString(1,title);
             pst.setString(2,note);
             pst.executeUpdate();
             JOptionPane.showMessageDialog(null,"Note Uploaded Sucessfully");
             
         }catch(SQLException ex){
             System.out.println("Error"+ex);
         }
     }
    
     
     public void getNote(int j){
         conn=DBConnection.getConnection();
         try{
             String query="select * FROM note where noteno="+j;
             pst=conn.prepareStatement(query);
             rs=pst.executeQuery();
             while(rs.next()){
                 title=rs.getString("title");
                 note =rs.getString("description");
                 
                 
             }
             
         }catch(Exception ex){
             System.out.println("Error"+ex);
         }
     }

    void getQuiz(int a) {
         conn=DBConnection.getConnection();
         try{
            
             String query="select * from quiz where quiz_no="+a;
             pst=conn.prepareStatement(query);
             rs=pst.executeQuery();
             while(rs.next()){
                  quizno=rs.getString("quiz_no");
                  question=rs.getString("question");
                  answer1=rs.getString("ans1");
                  answer2=rs.getString("ans2");
                  answer3=rs.getString("ans3");
                  answer4=rs.getString("ans4");
                  
                 // System.out.println(answer2);
                  
                 
             }
             
             
             
         }catch(Exception e){
             System.out.println("error:"+e);
             
         }
        
    }
    public int getCorrectCount(String uans,int c){
         
        
         conn=DBConnection.getConnection();
        
        try{
            
            String query="select c_ans from quiz where quiz_no="+c ;
            pst=conn.prepareStatement(query);
            //pst.setString(1, c)
                   // pst.setInt(1, c);
            
            rs=pst.executeQuery();
            rs.next();
             tempAns = rs.getString(1);
             
            temp = Integer.parseInt(tempAns);
             int UANS = Integer.parseInt(uans);
             
             
              if(temp==UANS)
        {
           mark =mark+1;
           
                System.out.println("mark:"+mark); 
        }
        
        
        
             System.out.println("Answer is  : " +tempAns);
             System.out.println("User Entered Answer is " +UANS);
            // System.out.println("Score:"+i+mark); 
            
           
          
            
            
        }catch(Exception e){
            System.out.println("error:"+e);
        }
        
        return mark;
        
    }
    
    public void changepassword(String uname,String opass,String npass,String cpass){
        conn=DBConnection.getConnection();
        try{
            String query="select password from student where username=?";
            pst=conn.prepareStatement(query);
            pst.setString(1,uname);
            rs=pst.executeQuery();
            if(rs.next() && (npass == null ? cpass == null : npass.equals(cpass))){
                String update="UPDATE student SET password=? where username=? ";
                pst=conn.prepareStatement(update);
                pst.setString(1,npass);
                pst.setString(2,uname);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"password changed succesfully");
               
            }else{
                JOptionPane.showMessageDialog(null,"your username or entered passwords are incorrect /n please try again");
            }
            
        }catch(Exception e){
            System.out.println("error:"+e);
        }
        
        
    }
    
    
   
   
     
}
