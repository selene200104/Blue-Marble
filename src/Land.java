
public class Land {

	String landName;			
	int villaPrice;				
	int buildingPrice;				
	int hotelPrice;
	int villaCheckCount;
	int buildingCheckCount;
	int hotelCheckCount;
	int constructionCost;
	
	//땅 이름, 빌라 가격, 빌딩 가격, 호텔 가격, 빌라 체크 횟수, 빌딩 체크 횟수, 호텔 체크 횟수
	Land(String landName, int villaPrice, int buildingPrice, int hotelPrice ,int villaCheckCount, int buildingCheckCount, int hotelCheckCount, int constructionCost){
		this.landName = landName;
		this.villaPrice = villaPrice;
		this.buildingPrice = buildingPrice;
		this.hotelPrice = hotelPrice;
		this.villaCheckCount = villaCheckCount;
		this.buildingCheckCount = buildingCheckCount;
		this.hotelCheckCount = hotelCheckCount;
		this.constructionCost = constructionCost;
		
	}
	
	
}
