package default_methods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface Test {

	String f(String s);

	default String m(String s) {
		BigDecimal big = new BigDecimal(1234);
		List<Date> l = new ArrayList<>(1234);
		return f(s + " - " + l);
	}

}
