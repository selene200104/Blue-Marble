
public class Land {

	String landName;
	String landowner;
	int villaPrice;				
	int buildingPrice;				
	int hotelPrice;
	int villaCheckCount;
	int buildingCheckCount;
	int hotelCheckCount;
	int amountVilla;
	int amountBuilding;
	int amountHotel;
	int constructionCost;
	
	//땅 이름, 빌라 가격, 빌딩 가격, 호텔 가격, 빌라 체크 횟수, 빌딩 체크 횟수, 호텔 체크 횟수
	Land(String landName, int villaPrice, int buildingPrice, int hotelPrice, int villaCheckCount, int buildingCheckCount, int hotelCheckCount, int amountVilla , int amountBuilding, int amountHotel , int constructionCost, String landowner){
		this.landName = landName;
		this.villaPrice = villaPrice;
		this.buildingPrice = buildingPrice;
		this.hotelPrice = hotelPrice;
		this.villaCheckCount = villaCheckCount;
		this.buildingCheckCount = buildingCheckCount;
		this.hotelCheckCount = hotelCheckCount;
		this.amountVilla = amountVilla;
		this.amountBuilding = amountBuilding;
		this.amountHotel = amountHotel;
		this.constructionCost = constructionCost;
		this.landowner = landowner;
		
	}
	
	
}
