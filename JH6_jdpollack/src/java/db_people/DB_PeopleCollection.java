/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_people;

import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class DB_PeopleCollection {
    
    /**
     *
     * @param statement
     * @param request
     * @return
     */
    public static String update(Statement statement, HttpServletRequest request){
        String error = "";
        String action = request.getParameter("action");
        if (action != null){
            String name = request.getParameter("name");
            String eye = request.getParameter("eye");
            String hair = request.getParameter("hair");
            String height = request.getParameter("height");
            String weight = request.getParameter("weight");
            DB_Person person = new DB_Person(name, eye, hair, height, weight);
            
            String sIndex;
            int index;
            
            switch(action){
                case "Clear List" : 
                    error = DB_Person.remove(-1, statement);
                    break;
                case "add" : 
                    error = person.add(statement);
                    break;
                case "remove" : 
                    sIndex = request.getParameter("index");
                    index = Integer.parseInt(sIndex);
                    error = DB_Person.remove(index, statement);
                    break;
                case "update" : 
                    sIndex = request.getParameter("index");
                    index = Integer.parseInt(sIndex);
                    error = person.update(index, statement);
                    break;
            }
        }
        ArrayList<DB_Person> pCollection = new ArrayList<>();
        error += DB_Person.getPeople(statement, pCollection);
        request.setAttribute("PeopleCollection", pCollection);
        
        return error;
    }
}
