/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.service.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FallBallRSResult {
    public int resultCode;
    public String message;
    public Object returnValue;
    
    public FallBallRSResult(int resultCode, String message, Object returnValue){
        this.resultCode = resultCode;
        this.message = message;
        this.returnValue = returnValue;
    }
    
    public FallBallRSResult(){//THIS (A NO ARG CTOR) IS CRUCIAL FOR JAX RS(JERSEY I GUESS) MESSAGE BODY WRITERS, 
        //THE XMLROOTELEMENT JAXB ANNOTATION NEED A DEFAULT EMPTY ARGG CONSTRUCTOR TO DO ITS SERIALIZATION JOB, I THINK THE SERIALIZATION IS DONE REGARDLESS OF THE ANNOTATION SINCE MY OTHER ENTITES DO NOT USE IT YET
    }
}
