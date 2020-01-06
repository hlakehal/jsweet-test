package quickstart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import annotation.TestAnnotation;

/**
 * This class is used within the webapp/index.html file.
 */
@TestAnnotation
public class QuickStart {

	public Date date;
	
	public static void main(String[] args) {
		System.out.println("COUCOU");
		// you can use regular Java API
		List<String> l = new ArrayList<>();
		l.add("Hello");
		l.add("world 2");
		l.add("world 3");
		System.out.println(l);
		// and you can also use regular JavaScript APIs
		//Array<String> a = new Array<>();
		//a.push("Hello", "world");
		// use of jQuery with the jQuery candy
		//$("#target").text(l.toString());
		// use of the JavaScript DOM API
		//alert(a.toString());
		BigDecimal d = new BigDecimal(10.2);
		BigDecimal d2 = BigDecimal.valueOf(10.2);
		BigDecimal d3 = BigDecimal.ONE;
		System.out.println(d.add(new BigDecimal(2)));
	}

}
