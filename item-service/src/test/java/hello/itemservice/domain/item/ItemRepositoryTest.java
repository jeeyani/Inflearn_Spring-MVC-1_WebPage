package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();

	/*테스트마다 초기화 */
	@AfterEach
	void afterEach() {
		itemRepository.clearStore();
	}

	@Test
	void save() {
		//given
		Item item = new Item("itemA", 10000, 10);
		
		//when
		Item savedItem = itemRepository.save(item);
		
		//then
		Item findItem = itemRepository.findById(item.getId());
		
		//저장된 아이템과 찾는 아이템이 같은지 비교하기
		assertThat(findItem).isEqualTo(savedItem);
	}

	@Test
	void findAll() {
		//given
		Item item1 = new Item("item1", 10000, 10);
		Item item2 = new Item("item2", 20000, 20);
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		//when
		List<Item> result = itemRepository.findAll();
		//then
		assertThat(result.size()).isEqualTo(2);
		//item1 과 item2 같은지 검증
		assertThat(result).contains(item1, item2);
	}

	@Test
	void updateItem() {
		//given
		Item item = new Item("item1", 10000, 10);
		Item savedItem = itemRepository.save(item);
		Long itemId = savedItem.getId();
		
		//when
		Item updateParam = new Item("item2", 20000, 30); //업데이트 테스트
		itemRepository.update(itemId, updateParam);
		Item findItem = itemRepository.findById(itemId);

		//then
		//수정한것과 이름이 동일한지 검증하기
		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
	}

}
