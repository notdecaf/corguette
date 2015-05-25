/*
 Example code based on code from Nicholas Smith at http://imnes.blogspot.com/2011/01/how-to-use-yelp-v2-from-java-including.html
 For a more complete example (how to integrate with GSON, etc) see the blog post above.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Example for accessing the Yelp API.
 */
public class Yelp extends JFrame{

  OAuthService service;
  Token accessToken;
  static int zip;
  GridBagConstraints c = new GridBagConstraints();
  /**
   * Setup the Yelp API OAuth credentials.
   *
   * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
   *
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  JPanel p0 = new JPanel();
  Icon i0 = new ImageIcon("images\\background2.jpg");
  JLabel l0 = new JLabel(i0);
  JButton b0 = new JButton("Begin");
  public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
    this.setSize(1000, 600);
    this.setLayout(new GridBagLayout());
    c.gridy = 1;
    c.gridx = 1;
    this.add(p0,c);
    c.gridy = 2;
    c.gridx = 1;
    p0.add(l0);
    this.add(b0,c);
    b0.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {enterZip();}  } );
    this.setVisible(true);
  }
  public void enterZip() {
	  this.setSize(300,400);
	  this.setVisible(false);
	  this.removeAll();
	  this.setLayout(new GridBagLayout());
	  c = new GridBagConstraints();
	  
  }

  /**
   * Search with term and location.
   *
   * @param term Search term
   * @param latitude Latitude
   * @param longitude Longitude
   * @return JSON string response
   */
  public String search(String term, double latitude, double longitude) {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("ll", latitude + "," + longitude);
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }
  public String search(String term, String location,int limit) {
	  OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
	  request.addQuerystringParameter("term", term);
	  request.addQuerystringParameter("location", location);
	  request.addQuerystringParameter("limit", ""+limit);
	  this.service.signRequest(this.accessToken, request);
	  Response response = request.send();
	    return response.getBody();
  }
  // CLI
  static Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    // Update tokens here from Yelp developers site, Manage API access.
    String consumerKey = "xoBfuWAfta2vogYhMsgNkg";
    String consumerSecret = "SzqKgJTN9VMrxmaBuzprD_psdPI";
    String token = "EaX1frcRQknqAOviejeu1dRgBZIha1cp";
    String tokenSecret = "hTPpgXt0ELV21x-gmEkSWLtv0r8";
    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
    WhatShouldIEat wsie = new WhatShouldIEat();
    System.out.print("Please input your zip code: ");
    zip = in.nextInt();
    String results = wsie.whereEat();
    //String response = yelp.search("San Francisco");
    System.out.print(results);
    String response = yelp.search(results,""+zip,1);
    //String response = yelp.search("Fastfood International","10012",1);
    double lati = Double.parseDouble(response.substring(response.indexOf("\"latitude\"")+12,response.indexOf(", \"longitude\"")));
    double longi = Double.parseDouble(response.substring(response.indexOf("\"longitude\"")+13,response.indexOf("}}")));
    System.out.println(response);
    String name = response.substring(response.indexOf("\"name\"")+9,response.indexOf("\", \"snippet_image_url"));
    String addr = "";
    if(response.matches("\"neighborhoods\""))
    	addr = response.substring(response.indexOf("\"display_address\"")+19, response.indexOf(", \"neighborhoods\""));
    else
    	addr = response.substring(response.indexOf("\"display_address\"")+19, response.indexOf(", \"postal_code\""));
    addr = addr.substring(2,addr.length()-2);
    System.out.println(addr);
    String[] addrsplit = addr.split("\", \"");
    addr = "";
    for(int i=0;i<addrsplit.length;i++)
    	addr+=addrsplit[i] + ", ";
    System.out.println(addr);
    System.out.print(name);
    System.out.println(lati);
    System.out.println(longi);
    try 
	{ 
	Process p=Runtime.getRuntime().exec("cmd /c start http://127.0.0.1:8080/maps3.html?lat="+lati+"?lon="+longi+"?name=\""+name+"\"?addr=\""+addr+"\""); 
	} 
	catch(IOException e1) {System.out.println(e1);} 

    System.out.println(response);
    System.out.println(results);
  }
}
