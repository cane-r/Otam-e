/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function changeHashOnLoad() {
        /*
		window.location.href += "#";
        setTimeout("changeHashAgain()", "1");
        */
    }

    function changeHashAgain() 
    {          
        window.location.href += "1";
        
    }

    var storedHash = window.location.hash;
    
    window.setInterval(function () {
        if (window.location.hash !== storedHash) {
            window.location.hash = storedHash;
        }
    },1);
    

function limit(field, limit) {
    if (field.value.length > limit) {
        field.value = field.value.substring(0,limit);
    } 
}


function refresh() {
    location.reload();
    
}

function validateRegistration(){

   var m=validateMail("r-form:mail");
   var v=validateDepartment();
   var g=validateGender();
   var y=validateYear();
   var p=validatePassword();
    
    
   if(!m || !v  || !y || !p || !g){
       return false;
   }
    
   else{
    //document.getElementById("r-form:submit").submit();
  setTimeout(function(){ document.getElementById("r-form:submit").disabled=true; }, 20);
    return true;
    }
}

function validateDepartment(){
    var dep=document.getElementById("r-form:department").value;
    if(dep=="" || dep==null  || dep==" "){
        
        document.getElementById("r-form:department").style.borderColor="#ec0f0f"
        document.getElementById("error-d").innerHTML="Bitte wählen Sie Ihr Studienfach";
        return false;
    }
    else{
        
        document.getElementById("error-d").innerHTML="";
        document.getElementById("r-form:department").style.borderColor="";
        return true;
    }
}


function validatePassword(){
    var p=document.getElementById("r-form:password");
    var p2=document.getElementById("r-form:password2");
    var pval=p.value;
    var pval2=p2.value;

if(pval=="" || pval==null || pval==" "){
        
        document.getElementById("error-p").innerHTML="Bitte geben Sie ein Passwort ein";
        p.style.borderColor="#ec0f0f";
        return false;
    }



    if(pval2=="" || pval2==null || pval2==" ") {
        
        document.getElementById("error-p2").innerHTML="Bitte wiederholen Sie Ihr Passwort";
        p2.style.borderColor="#ec0f0f";
        return false;
    }



    if(pval!=pval2){
        
        document.getElementById("error-p").innerHTML="Passwörter stimmen nicht überein";
        document.getElementById("error-p2").innerHTML="Passwörter stimmen nicht überein";
        p.style.borderColor="#ec0f0f";
        p2.style.borderColor="#ec0f0f";
        return false;
    }
    
    
    
    if(pval.indexOf(" ") > -1 || pval2.indexOf(" ")>-1){
       
       document.getElementById("error-p").innerHTML="Passwort dürfen keine Leerzeichen enthalten";
       document.getElementById("error-p2").innerHTML="";
       return false;
    }
    
    if(pval.length < 5 || pval.length > 12 || pval2.length <5 || pval2.length > 12){
       
       document.getElementById("error-p").innerHTML="Passwörter müssen mindestens 5 und maximal 12 Zeichen haben";
       document.getElementById("error-p2").innerHTML="";
       return false;
    }
    
    else{
        
        
        document.getElementById("error-p").innerHTML="";
        document.getElementById("error-p2").innerHTML="";
        p.style.borderColor="";
        p2.style.borderColor="";
        return true;
    }
}



function validatePasswordL(){
    var p=document.getElementById("l-form:password");
    var pval=p.value;
    
    
    if(pval=="" || pval==null || pval==" "){
        
        p.placeholder ="Bitte Passwort eingeben.."
        p.style.borderColor="#ec0f0f";
        return false;
    }
    
    
    if(pval.indexOf(" ") > -1){
       
       return false;
    }
    
    if(pval.length < 4 || pval.length > 12){
       
       return false;
    }
    
    else{
        
       
        p.style.borderColor="";
        return true;
    }
}


function validateGender(){
    
    var m=document.getElementById("r-form:gender:0").checked==true || document.getElementById("r-form:gender:1").checked==true || document.getElementById("r-form:gender:2").checked==true;
    if(!m){
        document.getElementById("error-g").innerHTML="Bitte geben Sie Ihr Geschlecht an";
        return false;
    }
    else{
        document.getElementById("error-g").innerHTML="";
        return true;
    
}

}


function validateYear(){
    var date=document.getElementById("r-form:date");
    var stype=date.value;
    if(stype=="" || stype==null || stype==" "){
        document.getElementById("r-form:date").style.borderColor="#ec0f0f"
        document.getElementById("error-b").innerHTML="Bitte wählen Sie Ihr Geburtsjahr";
         return false;
    }
    else{
        document.getElementById("error-b").innerHTML="";
        document.getElementById("r-form:date").style.borderColor="";
        
        return true;
    }
}


function validateMail(id){
 var pattern = new RegExp("^\\S+@\\S+\\.\\S+$");
    var mail=document.getElementById(id);
    var value=mail.value;
    
    if(value=="" || value==null || value==" "){
        
        mail.style.borderColor="#ec0f0f";
        mail.placeholder="Email eingeben";
        if(id == 'r-form:mail'){
			document.getElementById("error-m").innerHTML="Bitte geben Sie Ihre Email Adresse ein";
		}
    return false;
    }
    if(!pattern.test(value)){
    	if(id == 'r-form:mail'){
        	document.getElementById("error-m").innerHTML="Bitte geben Sie eine gültige Email Adresse ein";
        }
        mail.style.borderColor="#ec0f0f";
        return false;
    }
    /*
    if(value.indexOf("@campus.lmu.de")<0 || value.indexOf("@")!=value.lastIndexOf("@") || 
 value.indexOf("campus.lmu.de")!=value.lastIndexOf("campus.lmu.de") 
 || value.indexOf("lmu.de")!=value.lastIndexOf("lmu.de")
            ){
        console.log("mail format invalid..");
        /*|| endsWith(mail,"lmu.de")   || 
 mail.indexOf("@")!=mail.lastIndexOf("@") || 
 mail.indexOf("campus.lmu.de")!=mail.lastIndexOf("campus.lmu.de")
        
        mail.style.borderColor="#ec0f0f";
        
        return false;
    }
    */
    else
    {
        mail.style.borderColor="";
        mail.placeholder="";
    	if(id == 'r-form:mail'){
        	document.getElementById("error-m").innerHTML="";
        }        
        return true;
    }
   
}

function endsWith(str,s){
    return str.length >= s.length && str.substr(str.length - s.length) == s;
}






function validateLogin(){
    var m=validateMail("l-form:mail");
    var p=validatePasswordL();
    //!m || 
    if(document.getElementById("l-form:mail").value=="" || document.getElementById("l-form:mail").value==" " ||  document.getElementById("l-form:mail").value==null || !p){
        return false;
    }
    else{
        
        setTimeout(function(){ document.getElementById("l-form:submit").disabled=true; }, 20);
        return true;
      //document.getElementById("l-form").submit();
      
    }
}

function validateChangePassword(){
    var p=validatePassword();

    if(!p){
        return false;
    }

    else{
        setTimeout(function(){ document.getElementById("r-form:submit").disabled=true; }, 20);
        return true;
    }

}

function validateRecovery(){
    var p=validateMail("r-form:mail");

    if(!p){
        return false;
    }

    else{
        setTimeout(function(){ document.getElementById("r-form:submit").disabled=true; }, 20);
        return true;
    }

}

    