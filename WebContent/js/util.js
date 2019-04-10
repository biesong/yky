	 var day1 = new Date();
	 day1.setTime(day1.getTime()-24*60*60*1000);
	 var s1 = day1.getFullYear()+"/" + (day1.getMonth()+1) + "/" + day1.getDate();
	 function appendZero(obj) {
	        if(obj<10) return "0" +""+ obj;
	        else return obj;
	 } 
	 
	 