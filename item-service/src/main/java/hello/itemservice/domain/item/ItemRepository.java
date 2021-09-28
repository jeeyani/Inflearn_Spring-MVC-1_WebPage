package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
	/** HashMap사용시 멀티쓰레드의 환경에 적합하지 않음, 이럴경우 ConcurrentHashMap<> 사용을 권장*/
	private static final Map<Long, Item> store = new HashMap<>(); //static 사용
	private static long sequence = 0L; //static 사용

	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}

	public Item findById(Long id) {
		return store.get(id);
	}

	//전체조회
	public List<Item> findAll() {
		//ArrayList로 감싸서 리턴하게 되면 store 원 값이 변경되지 않음
		return new ArrayList<>(store.values());
	}

	public void update(Long itemId, Item updateParam) {
		//아이템 찾기
		Item findItem = findById(itemId);
		
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}
	
	public void clearStore() {
		store.clear();
	}
}
