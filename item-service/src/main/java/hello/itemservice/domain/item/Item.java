package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

	private Long id;
	private String itemName;
	private Integer price; //가격정보가 안들어 오는 경우도 취급하기 위해 Integer로 선언
	private Integer quantity; //수량정보가 안들어 오는 경우도 취급하기 위해 Integer로 선언
	 
	public Item() {
	}
	
	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
}
