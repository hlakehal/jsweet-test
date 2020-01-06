package varargs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestVarargs {

	public static void main(String[] args) {
		TestVarargs test = new TestVarargs();

		test.a(1, new String[] { "array", "var", "arg" });
		test.a(2, "array", "var", "arg");

		System.out.println(test.m1(2.1));
		System.out.println(test.m1(2.1, "a", "b"));
		System.out.println(test.m1("a"));

		System.out.println(test.m2(2.1));
		System.out.println(test.m2(2.1, "a"));
		System.out.println(test.m2("a"));
		System.out.println(test.m2("a", "b", "c"));

		Map<String, Long> map = new HashMap<>();
		map.put("un", 1L);
		map.put("deux", 2L);
		map.put("trois", 3L);

		test.subMapFromKeys(map, "un", "trois").forEach((v, k) -> {
			System.out.println("Map varargs <" + k + ", " + v + ">");
		});

		List<String> collection = new ArrayList<>();
		collection.add("deux");
		test.subMapFromKeys(map, collection).forEach((v, k) -> {
			System.out.println("Map Collection<" + k + ", " + v + ">");
		});
	}

	void a(int i, String... args) {
		System.out.println(args.length);
		System.out.println(args[0]);
	}

	public String m1(double p1, String... p2) {
		return "" + p1 + "-" + p2;
	}

	public String m1(double p1) {
		return "" + p1;
	}

	public String m1(String p1) {
		return "" + p1;
	}

	public String m2(double p1, String p2) {
		return "" + p1 + "-" + p2;
	}

	public String m2(double p1) {
		return "" + p1;
	}

	public String m2(String... p1) {
		return "" + p1;
	}

	@SuppressWarnings({ "unchecked" })
	public <E extends Map<K, V>, K, V> E subMapFromKeys(E aMap, K... anArrayKey) {
		if (aMap == null) {
			return (E) new LinkedHashMap<K, V>();
		}

		try {
			E newMap = (E) aMap.getClass().newInstance();
			if (anArrayKey == null) {
				return newMap;
			}
			// KPF afin de garder l'ordre dans le <aMap>, il faut le faire comme le suivant
			for (Map.Entry<K, V> anElement : aMap.entrySet()) {
				K aKey = anElement.getKey();

				if (contains(anArrayKey, aKey)) {
					newMap.put(aKey, anElement.getValue());
				}
			}
			return newMap;

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * Renvoie une nouvelle map alimentée à partir de aMap avec seulement les clés
	 * définies dans aCollectionKey
	 *
	 * @param aMap
	 *            est la map à inspecter
	 * @param aCollectionKey
	 *            est la collection des clés à considérer
	 * @return une instance de {@link Map}
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public <E extends Map<K, V>, K, V> E subMapFromKeys(E aMap, Collection<K> aCollectionKey) {
		if (aMap == null) {
			return (E) new LinkedHashMap<K, V>();
		}
		E newMap;
		try {
			newMap = (E) aMap.getClass().newInstance();
			if (aCollectionKey == null) {
				return newMap;
			}
			// KPF afin de garder l'ordre dans le <aMap>, il faut le faire comme le suivant
			for (Map.Entry<K, V> anElement : aMap.entrySet()) {
				K aKey = anElement.getKey();

				if (aCollectionKey.contains(aKey)) {
					newMap.put(aKey, anElement.getValue());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return newMap;
	}

	final <T> boolean contains(T[] anArrayObject, T anObject) {
		if (anArrayObject == null) {
			return false;
		} else {
			if (anObject == null) {
				for (int i = 0, length = anArrayObject.length; i < length; i++) {
					if (anArrayObject[i] == null) {
						return true;
					}
				}
			} else {
				for (int i = 0, length = anArrayObject.length; i < length; i++) {
					if (anObject.equals(anArrayObject[i])) {
						return true;
					}
				}
			}
			return false;
		}
	}

}
