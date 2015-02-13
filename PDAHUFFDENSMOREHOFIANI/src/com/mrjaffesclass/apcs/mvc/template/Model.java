package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables
  String name; // Name of user (input by user) 
  String gender; // Gender of user (input by user)
  String hairColor;
  String eyeColor;
  int userAge;  
  int height;   //height in inches
  String answerString;
  
  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:changeButton", this);
    mvcMessaging.subscribe("view:hairColorChanged", this);
    mvcMessaging.subscribe("view:eyeColorChanged", this);
    mvcMessaging.subscribe("view:GenderChanged", this);
    mvcMessaging.subscribe("view:AgeChanged", this);
    mvcMessaging.subscribe("view:HeightChanged", this);
    
    
  gender = "Male"; // Gender of user (input by user)
  hairColor = "Brown";
  eyeColor = "Blue";
  userAge = 50;  
  height = 60;   //height in inches
  answerString = "Please input data.";
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    
    if (messageName == "view:hairColorChanged") {
        hairColor = messagePayload.toString();
    }
    if (messageName == "view:eyeColorChanged") {
        eyeColor = messagePayload.toString();
    }
    if (messageName == "view:GenderChanged") {
        gender = messagePayload.toString();
    }
    if (messageName == "view:AgeChanged") {
        //userAge = new Int(messagePayload.toString());
        userAge = (int)messagePayload;
    }
    if (messageName == "view:HeightChanged") {
        height = (int)messagePayload;
    }

    //answerString = "Your ideal date is ";
    long PDA1, PDA2; 
    PDA1= Math.round((userAge*(.5))+7);
    PDA2 = Math.round((userAge - 7) * 2);
    int heightFeet = height/12;
    int heightInches = height%12;
    
    if (gender.equals("Male")) {
        //COMPUTES PDA

        answerString = "Your ideal date is a " + hairColor + " haired, " + eyeColor + " eyed, " + PDA1 + " to " + PDA2 + " year old woman who is " + Integer.toString(heightFeet) + " foot " + Integer.toString(heightInches)+ " inches tall.";
      
    } else if (gender.equals("Female")) {
        //COMPUTES PDA

        //Display PDA
        answerString = "Your ideal date is a " + hairColor + " haired, " + eyeColor + " eyed, " + PDA1 + " to " + PDA2 + " year old man who is " + Integer.toString(heightFeet) + " foot " + Integer.toString(heightInches)+ " inches tall.";

        }
    
    mvcMessaging.notify("model:answerChanged", answerString, true);
    
  }

  /**
   * Getter function for variable 1
   * @return Value of variable1
   */
  public String getHairColor() {
    return hairColor;
  }

  /**
   * Setter function for variable 1
   * @param v New value of variable1
   */
  public void setHairColor(String v) {
    
    // When we set a new value to variable 1 we need to also send a
    // message to let other modules know that the variable value
    // was changed
    //mvcMessaging.notify("model:variable1Changed", variable1, true);
  }
  
  /**
   * Getter function for variable 1
   * @return Value of variable2
   */
  public String getEyeColor() {
    return eyeColor;
  }
  
  /**
   * Setter function for variable 2
   * @param v New value of variable 2
   */
  public void setEyeColor(int v) {
  }

  public String getGender() {
    return gender;
  }
  
  /**
   * Setter function for variable 2
   * @param v New value of variable 2
   */
  public void setGender(int v) {
  }

  public int getAge() {
    return userAge;
  }
  public int getHeight() {
    return height;
  }

}
