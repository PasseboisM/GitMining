function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var full_name=GetQueryString("fn"); 
console.log(full_name);
// if(repotype!=null)	repotype = decodeURIComponent(repotype); 
// else				repotype = null;
