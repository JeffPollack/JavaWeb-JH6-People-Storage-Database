/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_Person {
    
    private String name;
    private String eye;
    private String hair;
    private String height;
    private String weight;
    int index;
    
    public DB_Person(String name, String eye, String hair, String height, String weight, int index){
        this.name=name;
        this.eye=eye;
        this.hair=hair;
        this.height=height;
        this.weight=weight;
        this.index=index;
    }
    public DB_Person(String name, String eye, String hair, String height, String weight){
        this(name, eye, hair, height, weight, -1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != getClass()){
            return false;
        }
        DB_Person p = (DB_Person) other;
//        if(name.equals(p.name) && eye.equals(p.eye)
//                && hair.equals(p.hair) && height.equals(p.height)
//                && weight.equals(p.weight)){
//            return true;
//        }else{
//            return false;
//        }
        return name.equals(p.name) && eye.equals(p.eye)
            && hair.equals(p.hair) && height.equals(p.height)
            && weight.equals(p.weight);
    }
    

    
    public String update(int index, Statement statement){
        String sql = "UPDATE PeopleCollection set name=" + q(name)
                + ", eye=" + q(eye) + ", hair=" + q(hair)
                + ", height=" + q(height) + ", weight=" + q(weight)
                + " WHERE id=" + index;
        return executeUpdate(sql, statement);
    }
    
    public String add(Statement statement){
        String sql = "SELECT name FROM PeopleCollection WHERE name=" + q(name);
        
        try{
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())
                return "There is already a person with that name";
        }catch (SQLException e){
            return e.toString();
        }
        sql = "INSERT INTO PeopleCollection VALUES(" + q(name) + ", "
                + q(eye) + ", " + q(hair) + ", "
                + q(height)+ ", " + q(weight)
                + ", null)";
        return executeUpdate(sql, statement);
    }
    
    public static String remove(int index, Statement statement){
        String sql = "DELETE from PeopleCollection ";
        if (index >= 0)
            sql += "WHERE id=" + index;
        return executeUpdate(sql, statement);
    }
    
    public static String getPeople(Statement statement, ArrayList<DB_Person> people){
        String error = "";
        try{
            String sql = "SELECT * FROM PeopleCollection";
            System.out.println("sql=" + sql);
            ResultSet rs = statement.executeQuery(sql);
            people.clear();
            while (rs.next()){
                String n = rs.getString("name");
                String e = rs.getString("eye");
                String h = rs.getString("hair");
                String he = rs.getString("height");
                String w = rs.getString("weight");
                int i = rs.getInt("id");
                DB_Person p = new DB_Person(n, e, h, he, w, i);
                people.add(p);
            }
        }catch(SQLException e){
            error = e.toString();
        }
        return error;
    }
    // Private methods
    private static String executeUpdate(String sql, Statement statement){
        String error = "";
        try{
            System.out.println("sql=" + sql);
            statement.executeUpdate(sql);
        }catch(SQLException e){
            error = e.toString();
        }
        return error;
    }    
        // Surround with single quote
    private static String q(String s) {
        return "\'" + s + "\'";
    }
}
