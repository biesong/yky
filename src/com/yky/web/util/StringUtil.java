package com.yky.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static void main(String[] args) {
		String str="[Sale|JZ155563201565EEB_190419080015392|2019/04/19 08:00:16|134.46|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632033FCAA5_190419080033448|2019/04/19 08:00:34|134.46|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632123EF99C_190419080203774|2019/04/19 08:02:05|1156.15|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556321437B898_190419080223432|2019/04/19 08:02:24|876.7|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632146871E4_190419080226339|2019/04/19 08:02:27|1183|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632159A3791_190419080239649|2019/04/19 08:02:40|1085.85|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632202BC863_190419080322362|2019/04/19 08:03:23|1163.35|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632286B19D1_190419080446102|2019/04/19 08:04:47|2275|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632308AC73A_190419080508681|2019/04/19 08:05:09|850.34|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563231257C2B_190419080512734|2019/04/19 08:05:13|1069.27|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556323190F791_190419080519813|2019/04/19 08:05:20|1209|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ155563237524395_190419080615117|2019/04/19 08:06:16|343.58|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632408CEDA1_190419080648712|2019/04/19 08:06:49|1209|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632436FAAED_190419080716583|2019/04/19 08:07:18|454.58|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632443D6E23_190419080723946|2019/04/19 08:07:25|454.58|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556324740EF32_190419080754515|2019/04/19 08:07:55|339.5|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563248779F95_190419080807326|2019/04/19 08:08:08|1231.76|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563254293565_190419080902028|2019/04/19 08:09:03|1178.16|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556325823CB82_190419080942969|2019/04/19 08:09:44|1001.07|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556326192B001_190419081019700|2019/04/19 08:10:21|339.37|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556327058477B_190419081145919|2019/04/19 08:11:48|362.05|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632707A0BAA_190419081147642|2019/04/19 08:11:49|1007|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632763A9EDE_190419081243756|2019/04/19 08:12:44|930|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555632897CF04C_190419081457799|2019/04/19 08:14:58|1166.86|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556329864664C_190419081626601|2019/04/19 08:16:27|1151.56|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556330347D4F2_190419081714779|2019/04/19 08:17:16|824.6|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633085D488E_190419081805304|2019/04/19 08:18:06|391.4|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556331371D490_190419081857469|2019/04/19 08:18:58|586.95|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633199A6ED2_190419081959180|2019/04/19 08:20:00|1080.25|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633305E39A2_190419082145270|2019/04/19 08:21:46|1271.44|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556333998658C_190419082319016|2019/04/19 08:23:20|836.82|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556335215B475_190419082521415|2019/04/19 08:25:22|844.3|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556335870FB38_190419082627606|2019/04/19 08:26:28|963.3|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556336373F2D5_190419082717795|2019/04/19 08:27:19|951.86|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563372729D9F_190419082847214|2019/04/19 08:28:48|583.3|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633767F146E_190419082927973|2019/04/19 08:29:29|1196.68|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556337792B12B_190419082939423|2019/04/19 08:29:40|1242.5|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ155563378289E85_190419082942058|2019/04/19 08:29:43|950|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633796B993B_190419082956763|2019/04/19 08:29:57|997.5|ZY|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633800F6868_190419083000072|2019/04/19 08:30:01|1242.5|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556338309F5FA_190419083030416|2019/04/19 08:30:32|954.37|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563383285414_190419083032265|2019/04/19 08:30:33|1268.55|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555633863F9706_190419083103887|2019/04/19 08:31:05|873.05|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556339035BE21_190419083143381|2019/04/19 08:31:44|852.15|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563401660980_190419083336310|2019/04/19 08:33:37|712.79|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634020CCB23_190419083340489|2019/04/19 08:33:41|558|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634125C5A11_190419083525654|2019/04/19 08:35:27|636.29|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634189AE815_190419083629111|2019/04/19 08:36:30|1092.67|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563419258130_190419083632162|2019/04/19 08:36:33|704.9|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634195B573E_190419083635538|2019/04/19 08:36:36|652.12|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563422458B2E_190419083704466|2019/04/19 08:37:06|582.35|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634262135A4_190419083742112|2019/04/19 08:37:43|760|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556342895B366_190419083809779|2019/04/19 08:38:11|1087.98|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634391B04EC_190419083951816|2019/04/19 08:39:53|558|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556344096E8A1_190419084009663|2019/04/19 08:40:10|970.68|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634484699DB_190419084124142|2019/04/19 08:41:25|1152.6|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634495BD746_190419084135055|2019/04/19 08:41:36|1024.62|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634735D1FC2_190419084535225|2019/04/19 08:45:36|475|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556347881B209_190419084628548|2019/04/19 08:46:29|749.55|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634789A2145_190419084629806|2019/04/19 08:46:30|1119.93|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555634845D5F3E_190419084725531|2019/04/19 08:47:26|852.15|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556348774CB3F_190419084757860|2019/04/19 08:47:58|1285.73|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563489909FF1_190419084819214|2019/04/19 08:48:20|1240.51|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563509220F7B_190419085132806|2019/04/19 08:51:34|1471.36|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635100E107C_190419085140073|2019/04/19 08:51:41|503.5|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563514211AF8_190419085222870|2019/04/19 08:52:24|265.61|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635145B7B37_190419085225174|2019/04/19 08:52:26|399.37|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563518170958_190419085301380|2019/04/19 08:53:02|956.86|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635184AE599_190419085304492|2019/04/19 08:53:05|1044.05|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563523216995_190419085352430|2019/04/19 08:53:53|1123.79|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563527040B9B_190419085430575|2019/04/19 08:54:32|1121|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563532359690_190419085523071|2019/04/19 08:55:24|616.55|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556353395354B_190419085539507|2019/04/19 08:55:40|1291.98|08|NODEF|16692101040012923|weixinpay^^Refund|1038816660006412019041985415091|2019/04/19 08:55:46|1183|Process|NODEF|16692101040012923|WeiXinRefund^^Sale|JZ15556355049D4D1_190419085824462|2019/04/19 08:58:25|340.1|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635586BF2C7_190419085946104|2019/04/19 08:59:47|1016.5|08|NODEF|16692101040012923|weixinpay^^Sale|JZ155563561510C01_190419090015912|2019/04/19 09:00:17|418.6|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635656E9E0A_190419090056929|2019/04/19 09:00:58|784.7|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556356896E756_190419090129113|2019/04/19 09:01:30|480|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635739262D5_190419090219150|2019/04/19 09:02:20|1522.68|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635755A573A_190419090235749|2019/04/19 09:02:37|1560.71|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556358522E611_190419090412966|2019/04/19 09:04:14|988.93|08|NODEF|16692101040012923|weixinpay^^Sale|JZ1555635911DDED2_190419090511202|2019/04/19 09:05:12|838.15|08|NODEF|16692101040012923|weixinpay^^Sale|JZ15556359376E0F5_190419090537903|2019/04/19 09:05:39|1303.7|08|NODEF|16692101040012923|weixinpay]";
		String[] strA=str.split("\\^\\^");
		List<String> res=new ArrayList<String>();
		for (String sa : strA) {
			res.add(sa);
		}
		 List<String> results = new ArrayList<String>();
		   Pattern pattern = Pattern.compile("JZ155563201565EEB");
		   for(int i=0; i < res.size(); i++){
		      Matcher matcher = pattern.matcher( res.get(i));
		      if(matcher.find()){
		         results.add(res.get(i));
		      }
		   }
		  String[] s1=results.get(0).split("\\|");
		  System.out.println(s1[1]);
	}
}