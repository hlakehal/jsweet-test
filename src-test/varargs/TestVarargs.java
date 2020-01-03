package varargs;

public class TestVarargs {

	public static void main(String[] args) {
		TestVarargs test = new TestVarargs();
		System.out.println(test.m1(2.1));
		System.out.println(test.m1(2.1, "a", "b"));
		System.out.println(test.m1("a"));
		
		System.out.println(test.m2(2.1));
		System.out.println(test.m2(2.1, "a"));
		System.out.println(test.m2("a"));
		System.out.println(test.m2("a", "b", "c"));
	}
	
	public String m1(double p1, String... p2) {
		return ""+p1+"-"+String.join(",", p2);
	}

	public String m1(double p1) {
		return ""+p1;
	}

	public String m1(String p1) {
		return ""+p1;
	}

	public String m2(double p1, String p2) {
		return ""+p1+"-"+String.join(",", p2);
	}

	public String m2(double p1) {
		return ""+p1;
	}

	public String m2(String... p1) {
		return ""+String.join(",", p1);
	}
	
	
}
