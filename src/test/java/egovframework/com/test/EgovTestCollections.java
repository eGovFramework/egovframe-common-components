package egovframework.com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Java 8 테스트용 불변 컬렉션 팩토리
 *
 * 5.1.0(JDK 17) 테스트 코드의 List.of / Set.of / Map.of 를 실행환경 4.3.2(JDK 1.8)에서 그대로 쓰기 위한 대체 메서드.
 * Java 9+ 팩토리와 같이 null 요소를 허용하지 않고, 수정 불가능한 컬렉션을 반환한다.
 *
 * <pre>
 *  수정일        수정자        수정내용
 *  ----------   --------   ---------------------------
 *  2026.11.08   유지보수        최초 생성 (실행환경 4.3.2 / JDK 1.8 적용)
 * </pre>
 */
public final class EgovTestCollections {

	private EgovTestCollections() {
	}

	@SafeVarargs
	public static <T> List<T> listOf(T... elements) {
		for (T element : elements) {
			requireNonNull(element);
		}
		return Collections.unmodifiableList(new ArrayList<>(Arrays.asList(elements)));
	}

	@SafeVarargs
	public static <T> Set<T> setOf(T... elements) {
		Set<T> set = new LinkedHashSet<>();
		for (T element : elements) {
			if (!set.add(requireNonNull(element))) {
				throw new IllegalArgumentException("duplicate element: " + element);
			}
		}
		return Collections.unmodifiableSet(set);
	}

	public static <K, V> Map<K, V> mapOf() {
		return Collections.emptyMap();
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1) {
		return build(k1, v1);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
		return build(k1, v1, k2, v2);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
		return build(k1, v1, k2, v2, k3, v3);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
			K k6, V v6) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
			K k6, V v6, K k7, V v7) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
			K k6, V v6, K k7, V v7, K k8, V v8) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
			K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9);
	}

	public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
			K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
		return build(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10);
	}

	/** Map.entry 대체 (수정 불가 엔트리) */
	public static <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new java.util.AbstractMap.SimpleImmutableEntry<>(requireNonNull(key), requireNonNull(value));
	}

	/** Map.ofEntries 대체 */
	@SafeVarargs
	public static <K, V> Map<K, V> mapOfEntries(Map.Entry<? extends K, ? extends V>... entries) {
		Map<K, V> map = new LinkedHashMap<>();
		for (Map.Entry<? extends K, ? extends V> e : entries) {
			if (map.put(requireNonNull(e.getKey()), requireNonNull(e.getValue())) != null) {
				throw new IllegalArgumentException("duplicate key: " + e.getKey());
			}
		}
		return Collections.unmodifiableMap(map);
	}

	@SuppressWarnings("unchecked")
	private static <K, V> Map<K, V> build(Object... keyValues) {
		Map<K, V> map = new LinkedHashMap<>();
		for (int i = 0; i < keyValues.length; i += 2) {
			K key = (K) requireNonNull(keyValues[i]);
			V value = (V) requireNonNull(keyValues[i + 1]);
			if (map.put(key, value) != null) {
				throw new IllegalArgumentException("duplicate key: " + key);
			}
		}
		return Collections.unmodifiableMap(map);
	}

	private static <T> T requireNonNull(T value) {
		if (value == null) {
			throw new NullPointerException();
		}
		return value;
	}
}
