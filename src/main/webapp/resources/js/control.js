function check(m) {
    var c = 0;
    var valid;


    for (var i = 1; i <= m; i++) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:ptradio" + i + ":" + y + "").checked == false) {
                document.getElementById("q" + i).style.border = "1px solid #f00";
                valid = false;

            }
        }
    }

    for (var i = 1; i <= m; i++) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:ptradio" + i + ":" + y + "").checked == true) {
                c++;
                if ((i % 2) == 0) {

                    document.getElementById("q" + i).style.border = "1px solid #fff";

                } else {
                    document.getElementById("q" + i).style.border = "1px solid #dadada";

                }

            }

        }

    }
    if (c == m) {
        valid = true;
    }


    return valid;
}


function change(id) {

    if (id == "pretest-r-form:q1:0")

    {

        document.getElementById("pretest-r-form:q2").style.visibility = "visible";
        document.getElementById("q2").style.color = "";
    }

    if (id == "pretest-r-form:q1:1") {
        document.getElementById("pretest-r-form:q2").style.visibility = "hidden";
        document.getElementById("q2").style.color = "#ccc";
    }



    if (id == "pretest-r-form:q3:0")

    {

        document.getElementById("pretest-r-form:q4").style.visibility = "visible";
        document.getElementById("q4").style.color = "";
    }

    if (id == "pretest-r-form:q3:1") {
        document.getElementById("pretest-r-form:q4").style.visibility = "hidden";
        document.getElementById("q4").style.color = "#ccc";
    }


    if (id == "pretest-r-form:q5:0")

    {

        document.getElementById("pretest-r-form:q6").style.visibility = "visible";
        document.getElementById("q6").style.color = "";
        
        if(document.getElementById("pretest-r-form:q6:0").checked==true){
        document.getElementById("pretest-r-form:q7").style.visibility = "visible";
        document.getElementById("q7").style.color = "";
        document.getElementById("pretest-r-form:q8").style.visibility = "visible";
        document.getElementById("q8").style.color = "";
      }
        document.getElementById("pretest-r-form:q9").style.visibility = "visible";
        document.getElementById("q9").style.color = "";
    }

    if (id == "pretest-r-form:q5:1") {


        document.getElementById("pretest-r-form:q6").style.visibility = "hidden";
        document.getElementById("q6").style.color = "#ccc";
        document.getElementById("pretest-r-form:q7").style.visibility = "hidden";
        document.getElementById("q7").style.color = "#ccc";
        document.getElementById("pretest-r-form:q8").style.visibility = "hidden";
        document.getElementById("q8").style.color = "#ccc";
        document.getElementById("pretest-r-form:q9").style.visibility = "hidden";
        document.getElementById("q9").style.color = "#ccc";
    }



    if (id == "pretest-r-form:q6:0")

    {

        document.getElementById("pretest-r-form:q7").style.visibility = "visible";
        document.getElementById("q7").style.color = "";
        document.getElementById("pretest-r-form:q8").style.visibility = "visible";
        document.getElementById("q8").style.color = "";
    }

    if (id == "pretest-r-form:q6:1") {
        document.getElementById("pretest-r-form:q7").style.visibility = "hidden";
        document.getElementById("q7").style.color = "#ccc";
        document.getElementById("pretest-r-form:q8").style.visibility = "hidden";
        document.getElementById("q8").style.color = "#ccc";

    }


}

function validatePostTest(){
    var valid=check(15);
    var valid1,valid2,valid3;
    

    if(document.getElementById("pretest-r-form:q16").value.length<1){
        valid1=false;
        document.getElementById("pretest-r-form:q16").style.borderColor = "#f00";

    }

    if(document.getElementById("pretest-r-form:q17").value.length<1){
        valid2=false;
        document.getElementById("pretest-r-form:q17").style.borderColor = "#f00";

    }


    if(document.getElementById("pretest-r-form:ptradio18:0").checked==false && document.getElementById("pretest-r-form:ptradio18:1").checked==false){
        valid3=false;
        document.getElementById("q18").style.border = "1px solid #f00";

    }



    if(document.getElementById("pretest-r-form:q16").value.length>250){
        valid1=false;
        document.getElementById("pretest-r-form:q16").style.borderColor = "#f00";
        alert("Frage 16:Max 250 Zeichen");

    }

    if(document.getElementById("pretest-r-form:q17").value.length>250){
        valid2=false;
        document.getElementById("pretest-r-form:q17").style.borderColor = "#f00";
        alert("Frage 17:Max 250 Zeichen");
    }



    if(document.getElementById("pretest-r-form:q16").value.length>1 && document.getElementById("pretest-r-form:q16").value.length<250 ){
        valid1=true;
        document.getElementById("pretest-r-form:q16").style.borderColor = "";

    }

    if(document.getElementById("pretest-r-form:q17").value.length>1 && document.getElementById("pretest-r-form:q17").value.length<250){
        valid2=true;
        document.getElementById("pretest-r-form:q17").style.borderColor = "";

    }


    if(document.getElementById("pretest-r-form:ptradio18:0").checked==true || document.getElementById("pretest-r-form:ptradio18:1").checked==true){
        valid3=true;
        document.getElementById("q18").style.border = "";

    }

    if(valid && valid1 && valid2 && valid3){
        setTimeout(function() {
            document.getElementById("pretest-r-form:submit").disabled = true;
        }, 15);
        return true;
    }
    else{

        return false;
    }

 


}


function validate1() {
    var c = 0;
    
    var v1 = false;//1,3,5
    var v2 = false;//5
    var v3 = false;//9
    var v4 = false;//10

    var v5 = false;//general1
    var v6 = false;//general2..
   




//q5
v2= document.getElementById("pretest-r-form:q5:0").checked || document.getElementById("pretest-r-form:q5:1").checked; 
//q9
 v3 = document.getElementById("pretest-r-form:q9:0").checked || document.getElementById("pretest-r-form:q9:1").checked;
 //q10
 v4 = document.getElementById("pretest-r-form:q10:0").checked || document.getElementById("pretest-r-form:q10:1").checked ||
        document.getElementById("pretest-r-form:q10:2").checked || document.getElementById("pretest-r-form:q10:3").checked ||
        document.getElementById("pretest-r-form:q10:4").checked || document.getElementById("pretest-r-form:q10:5").checked ||
        document.getElementById("pretest-r-form:q10:6").checked;

    
    
   
    for (var i = 1; i <= 5; i += 2) {
        for (var y = 0; y <= 1; y++) {

             if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == false) {
                
                v1 = false;
                
                document.getElementById("q" + i).style.border = "1px solid #f00";
            }
        }
    }


if (!v2) {
        document.getElementById("q5").style.border = "1px solid #f00";

    }

if (v2) {
        document.getElementById("q5").style.border = "";


    }



 if (!v3) {
        document.getElementById("q9").style.border = "1px solid #f00";


    }

    if (v3) {
        document.getElementById("q9").style.border = "";


    }


    if (!v4) {
        document.getElementById("q10").style.border = "1px solid #f00";


    }
    if (v4) {
        document.getElementById("q10").style.border = "";


    }



    for (var i = 1; i <= 5; i += 2) {

        for (var y = 0; y <= 1; y++) {

            if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == true) {
                c++;
                document.getElementById("q" + i).style.border = "";
            }
        }
    }

    if (c == 3) {
        v1 = true;
        
    }


 



    if (document.getElementById("pretest-r-form:q1:0").checked == true && document.getElementById("pretest-r-form:q2").value.length > 0) {
        document.getElementById("q2").style.border = "";
        v5 = true;
    }

    if (document.getElementById("pretest-r-form:q3:0").checked == true && document.getElementById("pretest-r-form:q4").value.length > 0) {
        document.getElementById("q4").style.border = "";
        v5 = true;
    }



    if (document.getElementById("pretest-r-form:q6:0").checked == true && document.getElementById("pretest-r-form:q7").value.length > 0) {
        document.getElementById("q7").style.border = "";
        v6 = true;
    }


    if (document.getElementById("pretest-r-form:q6:0").checked == true && document.getElementById("pretest-r-form:q8").value.length > 0) {
        document.getElementById("q8").style.border = "";
        v6 = true;
    }





    if (document.getElementById("pretest-r-form:q1:0").checked == true && document.getElementById("pretest-r-form:q2").value.length < 1) {
        document.getElementById("q2").style.border = "1px solid #f00";
        v5 = false;
    }

    if (document.getElementById("pretest-r-form:q3:0").checked == true && document.getElementById("pretest-r-form:q4").value.length < 1) {
        document.getElementById("q4").style.border = "1px solid #f00";
        v5 = false;
    }

   if (document.getElementById("pretest-r-form:q6:0").checked == true && document.getElementById("pretest-r-form:q7").value.length < 1 ) {
        document.getElementById("q7").style.border = "1px solid #f00";
        v6 = false;
    }

   if (document.getElementById("pretest-r-form:q6:0").checked == true && document.getElementById("pretest-r-form:q8").value.length < 1) {
        document.getElementById("q8").style.border = "1px solid #f00";
        v6 = false;
    }

    if(document.getElementById("pretest-r-form:q6:0").checked==false && document.getElementById("pretest-r-form:q6:1").checked==false){
        document.getElementById("q6").style.border = "1px solid #f00";
    } 
    
    if(document.getElementById("pretest-r-form:q6:0").checked==true || document.getElementById("pretest-r-form:q6:1").checked==true){
        document.getElementById("q6").style.border = "";
    }


//fallback
/*
    if (document.getElementById("pretest-r-form:q5:1").checked == true && ((document.getElementById("pretest-r-form:q7").value.length > 1) || (document.getElementById("pretest-r-form:q8").value.length > 1)) ) {
        document.getElementById("q7").style.border = "";
         document.getElementById("q8").style.border = "";
        v5 = false;
    }

*/


    

    if (document.getElementById("pretest-r-form:q1:1").checked == true && document.getElementById("pretest-r-form:q3:1").checked == true) {
        //document.getElementById("q2").style.border = "";
        v5 = true;
    }

    if (document.getElementById("pretest-r-form:q3:1").checked == true) {
        document.getElementById("q4").style.border = "";
        
    }

 if (document.getElementById("pretest-r-form:q1:1").checked == true) {
        document.getElementById("q2").style.border = "";
        
    }

 if (document.getElementById("pretest-r-form:q1:1").checked == true) {
        document.getElementById("q2").style.border = "";
        
    }



    if (document.getElementById("pretest-r-form:q5:1").checked == true) {
        document.getElementById("q6").style.border = "";
        document.getElementById("q7").style.border = "";
        document.getElementById("q8").style.border = "";
        document.getElementById("q9").style.border = "";
        v6=true;
        v3=true;
       
    }

    if (document.getElementById("pretest-r-form:q6:1").checked == true) {
        document.getElementById("q7").style.border = "";
        document.getElementById("q8").style.border = "";
        v6=true;
    }


if (document.getElementById("pretest-r-form:q6:0").checked == true  &&  !(document.getElementById("pretest-r-form:q9:0").checked == true || document.getElementById("pretest-r-form:q9:1").checked == true)) {
        document.getElementById("q9").style.border = "1px solid #f00";
        v6 = false;
    }
/*
console.log("v1 is --> " + v1);
console.log("v2 is --> " + v2);
console.log("v3 is --> " + v3);
console.log("v4 is --> " + v4);
console.log("v5 is --> " + v5);
console.log("v6 is --> " + v6);
*/
    if (v1&&v2&&v3&&v4&&v5&&v6) {

        setTimeout(function() {
            document.getElementById("pretest-r-form:submit").disabled = true;
        }, 15);
        return true;
    } else{
        return false;
}


}

function validateFeedBack2() {
    var valid1, valid2;
    var c = 0;
    var c1 = 0;

    //pretest-r-form:q1,q3,q5,q7..radio
    //pretest-r-form:q2,q4,q6,q8..input..

    for (var i = 1; i <= 7; i += 3) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == false)
                document.getElementById("q" + i).style.border = "1px solid #f00";
            valid1 = false;

        }
    }

    for (var i = 2; i <= 8; i += 3) {

        if (document.getElementById("pretest-r-form:q"+i).value.length < 1 || document.getElementById("pretest-r-form:q"+(i+1)).value.length < 1 || document.getElementById("pretest-r-form:q"+i).value.length > 500 || document.getElementById("pretest-r-form:q"+(i+1)).value.length > 500) {
            
                   if(document.getElementById("pretest-r-form:q"+i).value.length < 1){
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "#f00";
            //document.getElementById("q" + i).style.borderColor = "#f00";
               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length < 1){
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "#f00";
            //document.getElementById("q" + (i+1)).style.borderColor = "#f00";
               }

 if(document.getElementById("pretest-r-form:q"+i).value.length > 500){
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "#f00";
            //document.getElementById("q" + i).style.borderColor = "#f00";

            alert("Max. 500 Zeichen");

               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length > 500){
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "#f00";
            //document.getElementById("q" + (i+1)).style.borderColor = "#f00";

            alert("Max. 500 Zeichen");

               } 


valid2 = false;


        }

    }


            




    for (var i = 1; i <= 7; i += 3) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == true) {
                document.getElementById("q" + i).style.border = "";
                c++;
            }
        }
    }

    if (c == 3) {
        valid1 = true;

    }



 for (var i = 2; i <= 8; i += 3) {

        if (document.getElementById("pretest-r-form:q"+i).value.length > 0 || document.getElementById("pretest-r-form:q"+(i+1)).value.length >0) {
            
                   if(document.getElementById("pretest-r-form:q"+i).value.length > 0  &&  document.getElementById("pretest-r-form:q"+i).value.length < 500){
                    c1++;
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "";
            //document.getElementById("q"+i).style.border = "";;
               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length > 0 &&  document.getElementById("pretest-r-form:q"+(i+1)).value.length < 500){
                c1++;
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "";
            //document.getElementById("q" + (i+1)).style.border = "";
               }  

            


        }

    }

if (c1 == 6) {

        valid2 = true;

    }



    if (valid1 && valid2) {

        return true;
    } else {

        return false;
    }




}




function validateFeedBack() {
    var valid1, valid2;
    var c = 0;
    var c1 = 0;

    //pretest-r-form:q1,q3,q5,q7..radio
    //pretest-r-form:q2,q4,q6,q8..input..

    for (var i = 1; i <= 10; i += 3) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == false)
                document.getElementById("q" + i).style.border = "1px solid #f00";
            valid1 = false;

        }
    }

    for (var i = 2; i <= 11; i += 3) {

        if (document.getElementById("pretest-r-form:q"+i).value.length < 1 || document.getElementById("pretest-r-form:q"+(i+1)).value.length < 1 || document.getElementById("pretest-r-form:q"+i).value.length > 500  || document.getElementById("pretest-r-form:q"+(i+1)).value.length > 500  ) {
            
                   if(document.getElementById("pretest-r-form:q"+i).value.length < 1){
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "#f00";
            //document.getElementById("q" + i).style.borderColor = "#f00";
               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length < 1){
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "#f00";
            //document.getElementById("q" + (i+1)).style.borderColor = "#f00";
               }

if(document.getElementById("pretest-r-form:q"+i).value.length > 500){
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "#f00";

            alert("Max. 500 Zeichen");

            //document.getElementById("q" + i).style.borderColor = "#f00";
            
               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length > 500){
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "#f00";
            //document.getElementById("q" + (i+1)).style.borderColor = "#f00";

            alert("Max. 500 Zeichen");
            
            

               } 



valid2 = false;


        }

    }


            




    for (var i = 1; i <= 10; i += 3) {
        for (var y = 0; y <= 6; y++) {

            if (document.getElementById("pretest-r-form:q" + i + ":" + y).checked == true) {
                document.getElementById("q" + i).style.border = "";
                c++;
            }
        }
    }

    if (c == 4) {
        valid1 = true;

    }



 for (var i = 2; i <= 11; i += 3) {

        if (document.getElementById("pretest-r-form:q"+i).value.length > 0 || document.getElementById("pretest-r-form:q"+(i+1)).value.length >0) {
            
                   if(document.getElementById("pretest-r-form:q"+i).value.length > 0 && document.getElementById("pretest-r-form:q"+i).value.length < 500){
                    c1++;
            document.getElementById("pretest-r-form:q" + i).style.borderColor = "";
            //document.getElementById("q"+i).style.border = "";;
               } 


               if(document.getElementById("pretest-r-form:q"+(i+1)).value.length > 0 && document.getElementById("pretest-r-form:q"+(i+1)).value.length < 500){
                c1++;
            document.getElementById("pretest-r-form:q"+(i+1)).style.borderColor = "";
            //document.getElementById("q" + (i+1)).style.border = "";
               }  

            


        }

    }

if (c1 == 8) {

        valid2 = true;

    }



    if (valid1 && valid2) {

        return true;
    } else {

        return false;
    }




}

function validateFB() {

    var v = validateFeedBack();

    if (v) {
        setTimeout(function() {
            document.getElementById("pretest-r-form:submit").disabled = true;
        }, 15);
        return true;

    } else {
        return false;
    }

}


function validateFB2() {

    var v = validateFeedBack2();

    if (v) {
        setTimeout(function() {
            document.getElementById("pretest-r-form:submit").disabled = true;
        }, 15);
        return true;

    } else {
        return false;
    }

}




function changeHashOnLoad() {
    /*
		window.location.href += "#";
        setTimeout("changeHashAgain()", "1");
        */
}

function changeHashAgain() {
    window.location.href += "1";

}

var storedHash = window.location.hash;
window.setInterval(function() {
    if (window.location.hash !== storedHash) {
        window.location.hash = storedHash;
    }
}, 1);

function validate(m) {
    var valid = check(m);
    if (valid) {
        setTimeout(function() {
            document.getElementById("pretest-r-form:submit").disabled = true;
        }, 15);
        return true;

    } else {
        return false;
    }

}