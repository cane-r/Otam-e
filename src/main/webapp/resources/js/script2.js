
window.onload=function(){

document.getElementById("audio:submit").disabled=true
enable(30000);

}
      

function enable(x){

	setTimeout(function(){ document.getElementById("audio:submit").disabled=false; }, x);
}


function disableButton(){

setTimeout(function(){ document.getElementById("audio:submit").disabled=true; }, 15);

}

window.onpageshow = function(event) {
    if (event.persisted) {
        window.location.reload() 
    }
};