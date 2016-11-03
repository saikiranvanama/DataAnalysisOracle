package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author saikiran
 */
//public class JsonParsing {
//    public void YelpUserParse(Connection con){
//        try {
//            FileReader fr = new FileReader("./src/yelp_user.json");
//            BufferedReader br = new BufferedReader(fr);
//            int count = 0;
//            PreparedStatement statement;
//            statement = con.prepareStatement("insert into YELPUSER (YELPING_SINCE, FUNNY, USEFUL, COOL, REVIEW_COUNT, USERNAME, USER_ID, FANS, AVERAGE_STARS, USERTYPE, COMPLIMENTS_PROFILE, COMPLIMENTS_FUNNY, COMPLIMENTS_CUTE, COMPLIMENTS_PLAIN, COMPLIMENTS_WRITER, COMPLIMENTS_NOTE, COMPLIMENTS_PHOTOS, COMPLIMENTS_HOT, COMPLIMENTS_COOL, COMPLIMENTS_MORE)\n" +
//                                                 "values(to_date(?,'yyyy-mm'), ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            con.setAutoCommit(false);
//            String line;
//            while ((line = br.readLine()) != null) 
//            {
//                
//                JSONParser parser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) parser.parse(line);
//
//                //Froom date 
//                String yelping_since = (String) jsonObject.get("yelping_since");
//                
//                //Votes Parsing
//                JSONObject votes = (JSONObject) jsonObject.get("votes");
//                String funny = votes.get("funny").toString();
//                String useful = votes.get("useful").toString();
//                String cool = votes.get("cool").toString();      
//                String review_count = jsonObject.get("review_count").toString();
//                               
//                String name = (String) jsonObject.get("name");
//                String user_id = (String) jsonObject.get("user_id");
//                                
//                String fans = jsonObject.get("fans").toString();
//                String average_stars = jsonObject.get("average_stars").toString();
//                String type = (String) jsonObject.get("type");
//                
//                JSONObject compliments = (JSONObject) jsonObject.get("compliments");
//                Object compliments_profile = compliments.get("profile");
//                Object compliments_funny = compliments.get("funny");
//                Object compliments_cute = compliments.get("cute");
//                Object compliments_plain = compliments.get("plain");
//                Object compliments_writer = compliments.get("writer");
//                Object compliments_note = compliments.get("note");
//                Object compliments_photos = compliments.get("photos");
//                Object compliments_hot = compliments.get("hot");
//                Object compliments_cool = compliments.get("cool");
//                Object compliments_more = compliments.get("more");
//                
//                
//                statement.setNString(1, yelping_since);
//                statement.setInt(2, Integer.parseInt(funny));
//                statement.setInt(3, Integer.parseInt(useful));
//                statement.setInt(4, Integer.parseInt(cool));
//                statement.setInt(5, Integer.parseInt(review_count));
//                statement.setNString(6, name);
//                statement.setNString(7, user_id);
//                statement.setInt(8, Integer.parseInt(fans));
//                statement.setFloat(9, Float.parseFloat(average_stars));
//                statement.setNString(10, type);
//                statement.setInt(11, (int)(compliments_profile == null ? 0 : Integer.parseInt(compliments_profile.toString())));
//                statement.setInt(12, (int)(compliments_funny == null ? 0 : Integer.parseInt(compliments_funny.toString())));
//                statement.setInt(13, (int)(compliments_cute == null ? 0 : Integer.parseInt(compliments_cute.toString())));
//                statement.setInt(14, (int)(compliments_plain == null ? 0 : Integer.parseInt(compliments_plain.toString())));
//                statement.setInt(15, (int)(compliments_writer == null ? 0 : Integer.parseInt(compliments_writer.toString())));
//                statement.setInt(16, (int)(compliments_note == null ? 0 : Integer.parseInt(compliments_note.toString())));
//                statement.setInt(17, (int)(compliments_photos == null ? 0 : Integer.parseInt(compliments_photos.toString())));
//                statement.setInt(18, (int)(compliments_hot == null ? 0 : Integer.parseInt(compliments_hot.toString())));
//                statement.setInt(19, (int)(compliments_cool == null ? 0 : Integer.parseInt(compliments_cool.toString())));
//                statement.setInt(20, (int)(compliments_more == null ? 0 : Integer.parseInt(compliments_more.toString())));
//                statement.addBatch();
//                count++;
//                
//                if(count % 3000 == 2999){
//                    statement.executeBatch();
//                    con.commit();
//                    statement.clearBatch();
//                }
//            }
//            statement.executeBatch();
//            con.commit();
//            statement.close();
//            fr.close();
//            br.close();
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException | SQLException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    } 
//    public void YelpFriends(Connection con) throws SQLException, ParseException, FileNotFoundException, IOException{
//         //Friends Parsing
//        con.setAutoCommit(false);
//        FileReader fr = new FileReader("./src/yelp_user.json");
//        PreparedStatement friendsPreparedStmt = con.prepareStatement("Insert into UserFriends (user_id, friendId) values (?, ?)");
//        BufferedReader br = new BufferedReader(fr);
//        int count = 0;
//        String line;
//        while ((line = br.readLine()) != null) 
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(line);
//            String user_id = (String) jsonObject.get("user_id");
//            JSONArray friends = (JSONArray) jsonObject.get("friends");
//            Iterator iterator = friends.iterator();
//            if(iterator.hasNext()){
//                while (iterator.hasNext()) {
//                    friendsPreparedStmt.setNString(1, user_id);
//                    String friendID = iterator.next().toString();
//                    friendsPreparedStmt.setNString(2, friendID);
//    //                System.out.println(user_id + " " + iterator.next());
//                    friendsPreparedStmt.addBatch();
//                    count++;
//                }
//            }
//            else
//            {
//                System.out.println(" No friends");
//            }
//            if(count % 3000 == 2999){
//                friendsPreparedStmt.executeBatch();
//                friendsPreparedStmt.clearBatch();
//            }
//            System.out.println(count+"\n");
//        }
//        friendsPreparedStmt.executeBatch();
//        con.commit();
//        fr.close();
//        br.close();         
//    } 
//    public void userelite(Connection con) throws FileNotFoundException, IOException, ParseException, SQLException{
//        //user elite Parsing
//        FileReader fr = new FileReader("./src/yelp_user.json");
//        BufferedReader br = new BufferedReader(fr);
//        int count = 0;
//        String line;
//        con.setAutoCommit(false);
//        PreparedStatement elitePreparedStmt = con.prepareStatement("Insert into userElite (user_id, elite) values (?, ?)");
//        while ((line = br.readLine()) != null) 
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(line);
//            String user_id = (String) jsonObject.get("user_id");
//            JSONArray elite = (JSONArray) jsonObject.get("elite");
//            Iterator iterator = elite.iterator();
//            if(iterator.hasNext()){
//                while (iterator.hasNext()) {
//                    elitePreparedStmt.setNString(1, user_id);
//                    elitePreparedStmt.setInt(2, Integer.parseInt(iterator.next().toString()));
//                    elitePreparedStmt.addBatch();
//                    count ++;
//                }
//            }
//            else
//            {
//                System.out.println(" No elites");
//            }
//            System.out.println( count +"  " + user_id + "\n");
//        }
//        elitePreparedStmt.executeBatch();
//        fr.close();
//        con.commit();
//    }
//    public void yelpBusiness(Connection con){
//        //Parse yelp business JSON
//        try {
//            FileReader fr = new FileReader("./src/yelp_business.json");
//            BufferedReader br = new BufferedReader(fr);
//            int count = 0;
//            PreparedStatement statement;
//            statement = con.prepareStatement("insert into YELPBUSINESS (business_id, full_address, openStatus, city, review_count, businessName, longitude, state, stars, latitude, bType)\n" +
//                                             "values(?,?,?,?,?,?,?,?,?,?,?)");
//            con.setAutoCommit(false);
//            String line;
//            while ((line = br.readLine()) != null) 
//            {
//                
//                JSONParser parser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) parser.parse(line);
//
//                //Business ID (PK)
//                String business_id = (String) jsonObject.get("business_id");               
//                String full_address = (String)jsonObject.get("full_address");      
//                String openStatus = jsonObject.get("open").toString();
//                String city = (String) jsonObject.get("city");
//                String review_count = jsonObject.get("review_count").toString();
//                String businessName = jsonObject.get("name").toString();
//                String longitude = jsonObject.get("longitude").toString();
//                String state = (String) jsonObject.get("state");
//                String stars = jsonObject.get("stars").toString();
//                String latitude = jsonObject.get("latitude").toString();
//                String bType = (String) jsonObject.get("type");
//                
//                statement.setNString(1, business_id);
//                statement.setNString(2, full_address);
//                statement.setNString(3, openStatus);
//                statement.setNString(4, city);
//                statement.setInt(5, Integer.parseInt(review_count));
//                statement.setNString(6, businessName);
//                statement.setFloat(7, Float.parseFloat(longitude));
//                statement.setNString(8, state);
//                statement.setFloat(9, Float.parseFloat(stars));
//                statement.setFloat(10, Float.parseFloat(latitude));
//                statement.setNString(11, bType);
//                statement.addBatch();
//                count++;
//                
//                if(count % 3000 == 2999){
//                    statement.executeBatch();
//                    con.commit();
//                    statement.clearBatch();
//                }
//            }
//            statement.executeBatch();
//            con.commit();
//            statement.close();
//            fr.close();
//            br.close();
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException | SQLException ex) {
//            Logger.getLogger(JsonParsing.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
//    public void yelpBusinessTimings(Connection con) throws SQLException, FileNotFoundException, IOException, ParseException{
//        //Business Timings parsing
//        con.setAutoCommit(false);
//        FileReader fr = new FileReader("./src/yelp_business.json");
//        PreparedStatement satatement = con.prepareStatement("insert into businesstimings values(?, to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'),to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'), to_date(?,'HH24:MI:SS'))");
//        BufferedReader br = new BufferedReader(fr);
//        int count = 0;
//        String line;
//        while ((line = br.readLine()) != null) 
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(line);
//            String user_id = (String) jsonObject.get("business_id");
//            
//            //Get Individual objects for every day
//            JSONObject hours = (JSONObject) jsonObject.get("hours");
//            JSONObject Monday = (JSONObject) hours.get("Monday");
//            JSONObject Tuesday = (JSONObject) hours.get("Tuesday");
//            JSONObject Wednesday = (JSONObject) hours.get("Wednesday");
//            JSONObject Thursday = (JSONObject) hours.get("Thursday");
//            JSONObject Friday = (JSONObject) hours.get("Friday");
//            JSONObject Saturday = (JSONObject) hours.get("Saturday");
//            JSONObject Sunday = (JSONObject) hours.get("Sunday");
//            
//            String MondayOpen = (String)(Monday == null ? "" : Monday.get("open"));
//            String MondayClose = (String)(Monday == null ? "" : Monday.get("close"));
//            String TuesdayOpen = (String)(Tuesday == null ? "" : Tuesday.get("open"));
//            String TuesdayClose = (String)(Tuesday == null ? "" : Tuesday.get("close"));
//            String WednesdayOpen = (String)(Wednesday == null ? "" : Wednesday.get("open"));
//            String WednesdayClose = (String)(Wednesday == null ? "" : Wednesday.get("close"));
//            String ThursdayOpen = (String)(Thursday == null ? "" : Thursday.get("open"));
//            String ThursdayClose = (String)(Thursday == null ? "" : Thursday.get("close"));
//            String FridayOpen = (String)(Friday == null ? "" : Friday.get("open"));
//            String FridayClose = (String)(Friday == null ? "" : Friday.get("close"));
//            String SaturdayOpen = (String)(Saturday == null ? "" : Saturday.get("open"));
//            String SaturdayClose = (String)(Saturday == null ? "" : Saturday.get("close"));
//            String SundayOpen = (String)(Sunday == null ? "" : Sunday.get("open"));
//            String SundayClose = (String)(Sunday == null ? "" : Sunday.get("close"));
//            
//            satatement.setString(1, user_id);
//            satatement.setString(2, MondayOpen);
//            satatement.setString(3, MondayClose);
//            satatement.setString(4, TuesdayOpen);
//            satatement.setString(5, TuesdayClose);
//            satatement.setString(6, WednesdayOpen);
//            satatement.setString(7, WednesdayClose);
//            satatement.setString(8, ThursdayOpen);
//            satatement.setString(9, ThursdayClose);
//            satatement.setString(10, FridayOpen);
//            satatement.setString(11, FridayClose);
//            satatement.setString(12, SaturdayOpen);
//            satatement.setString(13, SaturdayClose);
//            satatement.setString(14, SundayOpen);
//            satatement.setString(15, SundayClose);
//            
//            satatement.addBatch();
//            
//            if(++count%100 == 0){
//                satatement.executeBatch();
//                satatement.clearBatch();
//                con.commit();
//            }
//            System.out.println(count+"\n");
//        }
//        satatement.executeBatch();
//        con.commit();
//        satatement.close();
//        fr.close();
//        br.close();
//    }
//    public void yelpBusinessCategory(Connection con) throws SQLException, FileNotFoundException, IOException, ParseException{
//        //Business Category Parsing
//        con.setAutoCommit(false);
//        FileReader fr = new FileReader("./src/yelp_business.json");
//        PreparedStatement satatement = con.prepareStatement("insert into businessCategories values(?, ?)");
//        BufferedReader br = new BufferedReader(fr);
//        int count = 0;
//        String line;
//        while ((line = br.readLine()) != null) 
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(line);
//            String user_id = (String) jsonObject.get("business_id");
//            JSONArray categoryArray = (JSONArray) jsonObject.get("categories");
//            Iterator iterator = categoryArray.iterator();
//            while(iterator.hasNext()){
//                satatement.setString(1, user_id);
//                satatement.setString(2, iterator.next().toString());
//                satatement.addBatch();
//                count++;
//            }
//            
//            if(count%100 == 0){
//                satatement.executeBatch();
//                satatement.clearBatch();
//                con.commit();
//            }
//            System.out.println(count+"\n");
//        }
//        satatement.executeBatch();
//        con.commit();
//        satatement.close();
//        fr.close();
//        br.close();
//    }
//    public void yelpBusinessAttributes(Connection con) throws SQLException, FileNotFoundException, IOException, ParseException{
//        //Business Attributes Sorting
//        con.setAutoCommit(false);
//        FileReader fr = new FileReader("./src/yelp_business.json");
//        PreparedStatement statement = con.prepareStatement("insert into businessAttributes values(?, ?, ?)");
//        BufferedReader br = new BufferedReader(fr);
//        ArrayList<String> valueList = new ArrayList<String>();
//        valueList.add("true");
//        valueList.add("false");
//        valueList.add("yes");
//        valueList.add("no");
//
//        String line;
//        int count = 0;
//        while ((line = br.readLine()) != null) 
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(line);
//            String business_id = (String) jsonObject.get("business_id");
//            JSONObject attributes = (JSONObject) jsonObject.get("attributes");
//            Object[] keys = attributes.keySet().toArray();
//            Object[] list = attributes.values().toArray();
//            
//            for(int i =0; i != keys.length; i++){
//                String value = list[i].toString();
//                if(isJsonVerification(value)){
//                    JSONObject obj = (JSONObject) new JSONParser().parse(value);
//                    Object[] keys2 = obj.keySet().toArray();
//                    Object[] list2 = obj.values().toArray();
//                    for (int j = 0; j < keys2.length; j++) {
//                    	String value2 = list2[j].toString();
//                    	System.out.println(keys2[j].toString() + " " + value2);
//                    	if(valueList.contains(value2) ? true : false){
//                    		statement.setString(1, business_id);
//                            statement.setString(2, keys[i].toString()+"_"+keys2[j].toString());
//                            statement.setString(3, list2[j].toString());
//                    	} else{
//                    		statement.setString(1, business_id);
//                            statement.setString(2, keys[i].toString()+"_"+keys2[j].toString()+"_"+value2);
//                            statement.setString(3, "true");
//                    	}
//                        statement.addBatch();
//                        count++;
//                    }
//                }
//                else{
//                	System.out.println(keys[i].toString() + " " + value);
//                	if(valueList.contains(value) ? true : false){
//                		statement.setString(1, business_id);
//                        statement.setString(2, keys[i].toString());
//                        statement.setString(3, list[i].toString());
//                	}else{
//                		statement.setString(1, business_id);
//                        statement.setString(2, keys[i].toString()+"_"+list[i].toString());
//                        statement.setString(3, "true");
//                	}
//                    statement.addBatch();
//                    count++;
//                }
//                
//                if(count % 10000 == 9999){
//                	statement.executeBatch();
//                    con.commit();
//                    statement.clearBatch();
//                }
//            }
//        }
//        statement.executeBatch();
//        con.commit();
//        statement.close();
//        fr.close();
//        br.close();
//    }
//    public static boolean isJsonVerification(String str){
//        
//        if (str.trim().contains("{") || str.trim().contains("[")) {
//            return str.trim().charAt(0) == '{' || str.trim().charAt(0) == '[';
//        } else {
//            return false;
//        }                    
//    }
//    
//     public void Yelp_review(Connection con) {
//        try {
//            FileReader fr = new FileReader("./src/yelp_review.json");
//            BufferedReader br = new BufferedReader(fr);
//            int count = 0;
//            String file_line;
//            con.setAutoCommit(false);
//            PreparedStatement p_statement;
//            p_statement = con.prepareStatement("INSERT INTO YELP_REVIEW(REVIEW_ID, USER_ID, BUSINESS_ID, REVIEW_TYPE, FUNNY, USEFUL, COOL, STARS, REVIEW_DATE, TEXT) \n" +
//"VALUES(?, ?, ?, ?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd'), ? )");
//            while ((file_line = br.readLine()) != null)
//            {
//                JSONParser parser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) parser.parse(file_line);
//               
//                //Votes Parsing
//                JSONObject votes = (JSONObject) jsonObject.get("votes");
//                String funny = votes.get("funny").toString();
//                String useful = votes.get("useful").toString();
//                String cool = votes.get("cool").toString();
//                String review_id = (String) jsonObject.get("review_id");
//                String user_id = (String) jsonObject.get("user_id");
//                String stars = (String) jsonObject.get("stars").toString();
//                String date = (String) jsonObject.get("date");
//                String text = (String) jsonObject.get("text");
//                String type = (String) jsonObject.get("type");
//                String business_id = (String) jsonObject.get("business_id");
//               
//                p_statement.setString(1, review_id);
//                p_statement.setString(2, user_id);
//                p_statement.setString(3, business_id);
//                p_statement.setString(4, type);
//                p_statement.setInt(5, Integer.parseInt(funny));
//                p_statement.setInt(6, Integer.parseInt(useful));
//                p_statement.setInt(7, Integer.parseInt(cool));
//                p_statement.setFloat(8, Float.parseFloat(stars));
//                p_statement.setString(9, date);
//                p_statement.setString(10, text);
//                p_statement.addBatch();
////                p_statement.executeUpdate();
////                p_statement.close();
//                if(++count % 10000 == 0)
//                {
//                    p_statement.executeBatch();
//                    p_statement.clearBatch();
//                    con.commit();
//                    System.out.println(count+"\n");
//                }
////                count++;
////                System.out.println(count+"\n");
//            }
//            p_statement.executeBatch();
//            p_statement.clearBatch();
//            p_statement.close();
//            con.commit();
//            br.close();
//            fr.close();
////            count =1;
//        } catch (IOException ex) {
//                System.out.println(ex);
//        } catch (ParseException ex) {
//                System.out.println(ex);
//        } catch (NullPointerException ex) {
//                System.out.println(ex);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
//}
