package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

	//@RequiredArgsConstructor 사용하면 final이 붙은 변수는 생성자가 있다고 간주함
	private final ItemRepository itemRepository;

	//* 상세목록
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	//상품상세
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	
	//상품등록 폼 GET
	@GetMapping("/add")
	public String addForm() {
		
		return "basic/addForm";
	}
	
	//상품등록 폼 POST
	//@PostMapping("/add")
	public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
		
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	//상품등록 폼 POST - ModelAttribute
	//@PostMapping("/add")
	public String addItemV2(@ModelAttribute("item") Item item) {
		
		/*@ModelAttribute가 자동으로 만들어줌으로 삭제 가능~
		 * Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);*/
		
		itemRepository.save(item);
		
		//*@ModelAttribute("item")==> item 이름으로 모델에 자동으로 넣어줌 */
		//model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	/**
	* @ModelAttribute name 생략 가능
	* model.addAttribute(item); 자동 추가, 생략 가능
	* 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
	*/
	//@PostMapping("/add")
	public String addItemV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		
		return "basic/item";
	}
	
	/**
	* @ModelAttribute 자체 생략 가능
	* model.addAttribute(item) 자동 추가
	*/
	@PostMapping("/add")
	public String addItemV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}

	/**
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}
}
